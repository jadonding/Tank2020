package cn.jdblg.tank;

/**
 * @author Jadon
 * @create 2020-08-16-21:00
 */

import cn.jdblg.tank.strategy.FireStrategy;
import java.awt.*;
import java.awt.event.KeyEvent;

import static cn.jdblg.tank.TankFrame.GAME_HEIGHT;
import static cn.jdblg.tank.TankFrame.GAME_WIDTH;

public class Player extends GameObject{
    private int x;
    private int y;
    private Dir dir;
    private boolean bL, bU, bR, bD;
    private static boolean moving = false;
    public static final int SPEED = 7;
    private Group group;
    private boolean live = true;
    private int w = ResourceMgr.goodTankU.getWidth();
    private int h = ResourceMgr.goodTankU.getHeight();
    private boolean lStop = false;
    private boolean uStop = false;
    private boolean rStop = false;
    private boolean dStop = false;
    public Rectangle rect;
    private int oldX,oldY;
    public boolean islStop() {
        return lStop;
    }

    public void setlStop(boolean lStop) {
        this.lStop = lStop;
    }

    public boolean isuStop() {
        return uStop;
    }

    public void setuStop(boolean uStop) {
        this.uStop = uStop;
    }

    public boolean isrStop() {
        return rStop;
    }

    public void setrStop(boolean rStop) {
        this.rStop = rStop;
    }

    public boolean isdStop() {
        return dStop;
    }

    public void setdStop(boolean dStop) {
        this.dStop = dStop;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

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
                g.drawImage(ResourceMgr.goodTankL, this.x, this.y, null);
                break;
            case U:
                g.drawImage(ResourceMgr.goodTankU, this.x, this.y, null);
                break;
            case R:
                g.drawImage(ResourceMgr.goodTankR, this.x, this.y, null);
                break;
            case D:
                g.drawImage(ResourceMgr.goodTankD, this.x, this.y, null);
                break;
        }

    }


    public Rectangle getRect(){
        return this.rect;
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

    public static boolean isMoving() {
        return moving;
    }

    public static void setMoving(boolean moving) {
        Player.moving = moving;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }
    public void back(){
        this.x = oldX;
        this.y = oldY;
    }

    public void move() {
        oldX = this.x;
        oldY = this.y;
        if (!moving) return;
        switch (dir) {
            case L:
                if (!(x < 0) && !islStop()) this.x -= SPEED;
                break;
            case U:
                if (!(y < 30) && !isuStop()) this.y -= SPEED;
                break;
            case R:
                if (!(x > GAME_WIDTH - this.getW()) && !isrStop() ) this.x += SPEED;
                break;
            case D:
                if (!(y > GAME_HEIGHT - this.getH()) && !isdStop()) this.y += SPEED;
                break;
        }
        this.rect.x = x;
        this.rect.y = y;
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
        }

        setMainDir();
    }

    private void fire() {
        FireStrategy strategy = null;
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
        TankFrame.INSTANCE.gm.getGameObjects().add(new Explode(x, y));

//        System.exit(0);
    }
}
