package cn.jdblg.tank;

/**
 * @author Jadon
 * @create 2020-08-16-21:00
 */

import java.awt.*;
import java.util.List;
import java.util.Random;
import static cn.jdblg.tank.TankFrame.GAME_HEIGHT;
import static cn.jdblg.tank.TankFrame.GAME_WIDTH;

public class Tank extends GameObject{
    private int x, y ;
    private Dir dir;
    private static boolean moving = true;
    public static final int SPEED = 5;
    private Group group;
    private boolean live = true;
    private Rectangle rect;
    private int w = ResourceMgr.badTankU.getWidth();
    private int h = ResourceMgr.badTankU.getHeight();
    private int oldX,oldY;
    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
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

    public Tank(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        rect = new Rectangle(x,y,w,h);
    }

    public static boolean isMoving() {
        return moving;
    }

    public static void setMoving(boolean moving) {
        Tank.moving = moving;
    }

    public void paint(Graphics g) {
        if (!this.isLive()) return;
        switch (dir) {
            case L:
                g.drawImage(ResourceMgr.badTankL, x, y, null);
                break;
            case U:
                g.drawImage(ResourceMgr.badTankU, x, y, null);
                break;
            case R:
                g.drawImage(ResourceMgr.badTankR, x, y, null);
                break;
            case D:
                g.drawImage(ResourceMgr.badTankD, x, y, null);
                break;
        }
        move();
        this.rect.x = x;
        this.rect.y = y;
    }
    public void back(){
        x = oldX;
        y = oldY;
    }

    private void move() {
        oldX = x;
        oldY = y;
        if (!moving) return;
        switch (dir) {
            case L:
                if (!(x < 0) ) x -= SPEED;
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
//        boundsCheck();
        randomDir();
        if (r.nextInt(100) > 90) fire();
    }

    private Random r = new Random();

    private void randomDir() {
        if (r.nextInt(100) > 90) {
            this.dir = Dir.randomDir();
        }
    }

    private void fire() {
        int bX = x + ResourceMgr.goodTankU.getWidth() / 2 - ResourceMgr.bulletU.getWidth() / 2;
        int bY = y + ResourceMgr.goodTankU.getHeight() / 2 - ResourceMgr.bulletU.getHeight() / 2;
        TankFrame.INSTANCE.gm.getGameObjects().add(new Bullet(bX, bY, dir, group));

    }

    public void die() {
        this.setLive(false);
        TankFrame.INSTANCE.gm.getGameObjects().add(new Explode(x, y));
    }

    public Rectangle getRect() {
        return rect;
    }
}
