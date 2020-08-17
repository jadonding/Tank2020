package cn.jdblg.tank;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Jadon
 * @create 2020-08-16-21:00
 */


public class TankFrame extends Frame {
    private Tank myTank;
    private Tank enemy;
    private Bullet bullet;
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;

    public TankFrame () {
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
        myTank = new Tank(100, 100, Dir.R, Group.GOOD,this);
        enemy = new Tank(200, 200, Dir.D, Group.BAD,this);
        bullet = new Bullet(100,100,Dir.D,Group.BAD);
    }

    @Override
    public void paint(Graphics g) {
        myTank.paint(g);
        enemy.paint(g);
        bullet.paint(g);
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
        this.bullet = bullet;
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