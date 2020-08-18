package cn.jdblg.tank;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jadon
 * @create 2020-08-16-21:00
 */


public class TankFrame extends Frame {
    public static final TankFrame INSTANCE = new TankFrame();
    public static final int GAME_WIDTH = 1280;
    public static final int GAME_HEIGHT = 720;
    private Player myTank;
    private List<Bullet> bullets;
    private List<Tank> tanks;
    private List<Explode> explodes;


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
        initGameObj();

    }

    private void initGameObj() {
        myTank = new Player(100, 100, Dir.R, Group.GOOD);
        tanks = new ArrayList<>();
        bullets = new ArrayList<>();
        explodes = new ArrayList<>();
        int initTankCount = Integer.parseInt(PropertyMgr.get("initTankCount"));
        for (int i = 0; i < initTankCount; i++) {
            tanks.add(new Tank(100 + 50 * i, 200, Dir.D, Group.BAD));
        }
    }


    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("bullets:" + bullets.size(), 10, 50);
        g.drawString("enemies:" + tanks.size(), 10, 65);
        g.drawString("explodes:" + explodes.size(), 10, 80);
        g.setColor(c);
        myTank.paint(g);
        for (int i = 0; i < tanks.size(); i++) {
            if (!tanks.get(i).isLive()) {
                tanks.remove(i);
            } else {
                tanks.get(i).paint(g);
            }
        }
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collidesWithTank(tanks.get(j));
            }
            if (!bullets.get(i).isLive()) {
                bullets.remove(i);
            } else {
                bullets.get(i).paint(g);
            }
        }
        for (int i = 0; i < explodes.size(); i++) {
            if (!explodes.get(i).isLive()) {
                explodes.remove(i);
            } else {
                explodes.get(i).paint(g);
            }
        }
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

    public void add(Bullet bullet) {
        this.bullets.add(bullet);

    }

    public void add(Explode explode) {
        this.explodes.add(explode);
    }

    private class TankKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            myTank.keyPressed(e);
        }


        @Override
        public void keyReleased(KeyEvent e) {
            myTank.keyReleased(e);
        }
    }
}