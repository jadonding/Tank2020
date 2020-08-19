package cn.jdblg.tank;

/**
 * @author Jadon
 * @create 2020-08-16-21:00
 */

import cn.jdblg.tank.strategy.DefaultFireStrategy;
import cn.jdblg.tank.strategy.FireStrategy;
import cn.jdblg.tank.strategy.FourDirFireSrategy;
import cn.jdblg.tank.strategy.LRFireSrategy;

import java.awt.*;
import java.awt.event.KeyEvent;

import static cn.jdblg.tank.TankFrame.GAME_HEIGHT;
import static cn.jdblg.tank.TankFrame.GAME_WIDTH;

public class Player extends GameObject{
    private int x;
    private int y;
    private int oldX,oldY;
    private Dir dir;
    private boolean bL, bU, bR, bD;
    private static boolean moving = false;
    public static final int SPEED = 7;
    private Group group;
    private boolean live = true;
    private int w = ResourceMgr.goodTankU.getWidth();
    private int h = ResourceMgr.goodTankU.getHeight();
    private Rectangle rect;
    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isbL() {
        return bL;
    }

    public void setbL(boolean bL) {
        this.bL = bL;
    }

    public boolean isbU() {
        return bU;
    }

    public void setbU(boolean bU) {
        this.bU = bU;
    }

    public boolean isbR() {
        return bR;
    }

    public void setbR(boolean bR) {
        this.bR = bR;
    }

    public boolean isbD() {
        return bD;
    }

    public void setbD(boolean bD) {
        this.bD = bD;
    }

    public static int getSPEED() {
        return SPEED;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Player(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        rect = new Rectangle(x,y,w,h);
    }


    public void paint(Graphics g) {
        if (!this.isLive()) return;
        switch (dir) {
            case L:
                g.drawImage(ResourceMgr.goodTankL, x, y, null);
                break;
            case U:
                g.drawImage(ResourceMgr.goodTankU, x, y, null);
                break;
            case R:
                g.drawImage(ResourceMgr.goodTankR, x, y, null);
                break;
            case D:
                g.drawImage(ResourceMgr.goodTankD, x, y, null);
                break;
        }

        move();
        this.rect.x = x;
        this.rect.y = y;
    }
    public Rectangle getRect(){
        return rect;
    }
    public void back(){
        x = oldX;
        y = oldY;
    }


    private void setMainDir() {
        if (!bL && !bU && !bR && !bD)
            moving = false;
        else {
            moving = true;
            if (bL && !bU && !bR && !bD)
                dir = Dir.L;
            if (!bL && bU && !bR && !bD)
                dir = Dir.U;
            if (!bL && !bU && bR && !bD)
                dir = Dir.R;
            if (!bL && !bU && !bR && bD)
                dir = Dir.D;
        }
    }

    private void move() {
        oldX = x;
        oldY = y;
        if (!moving) return;
        switch (dir) {
            case L:
                if (!(x < 0)) x -= SPEED;
                break;
            case U:
                if (!(y < 30)) y -= SPEED;
                break;
            case R:
                if (!(x > GAME_WIDTH - ResourceMgr.badTankU.getWidth())) x += SPEED;
                break;
            case D:
                if (!(y > GAME_HEIGHT - ResourceMgr.badTankU.getHeight())) y += SPEED;
                break;

        }
//        new Thread(()->new Audio("audio/tank_move.wav").play()).start();
    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_LEFT:
                bL = true;
                break;
            case KeyEvent.VK_UP:
                bU = true;
                break;
            case KeyEvent.VK_RIGHT:
                bR = true;
                break;
            case KeyEvent.VK_DOWN:
                bD = true;
                break;
            case KeyEvent.VK_CONTROL:
                fire();
                break;
        }

        setMainDir();

    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                bL = false;
                break;
            case KeyEvent.VK_UP:
                bU = false;
                break;
            case KeyEvent.VK_RIGHT:
                bR = false;
                break;
            case KeyEvent.VK_DOWN:
                bD = false;
                break;
//            case KeyEvent.VK_CONTROL:
//                fire();
//                break;
        }

        setMainDir();
    }

    private void fire() {
        //        FireStrategy strategy = new FourDirFireSrategy();
        FireStrategy strategy = null;
//        ClassLoader classLoader = Player.class.getClassLoader();
//        try {
//            Class clazz = classLoader.loadClass("cn.jdblg.tank.strategy." + PropertyMgr.get("tankFireStrategy"));
//            strategy = (FireStrategy)(clazz.getDeclaredConstructor().newInstance());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            Class clazz = Class.forName("cn.jdblg.tank.strategy." + PropertyMgr.get("tankFireStrategy"));
            strategy = (FireStrategy)(clazz.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        strategy.fire(this);

    }

    public void die() {
        this.setLive(false);
    }
}
