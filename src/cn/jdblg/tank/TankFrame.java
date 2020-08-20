package cn.jdblg.tank;

import org.junit.jupiter.api.Assertions;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * @author Jadon
 * @create 2020-08-16-21:00
 */


public class TankFrame extends Frame {
    public static final TankFrame INSTANCE = new TankFrame();
    public static final int GAME_WIDTH = 1280;
    public static final int GAME_HEIGHT = 720;
    public GameModel gm = new GameModel();

    private TankFrame() {
        this.setTitle("坦克大战————by:Jadon");
        this.setLocation(400, 100);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.addKeyListener(new TankKeyListener());
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

        });

    }


    @Override
    public void paint(Graphics g) {
        gm.paint(g);
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    private class TankKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
            if (key == KeyEvent.VK_S) save();
            else if (key == KeyEvent.VK_L) load();
            else gm.getMyTank().keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            gm.getMyTank().keyReleased(e);
        }
    }

    private void load() {
        ObjectInputStream ois = null;
        try {
            File file = new File("E://test/tank.dat");
            FileInputStream fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            GameModel gm = (GameModel) (ois.readObject());
            this.gm = gm;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void save() {
        ObjectOutputStream oos = null;
        try {
            File file = new File("E://test/tank.dat");
            FileOutputStream fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(this.gm);
            oos.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}