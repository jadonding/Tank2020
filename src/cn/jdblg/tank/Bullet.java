package cn.jdblg.tank;

import java.awt.*;

import static cn.jdblg.tank.TankFrame.GAME_HEIGHT;
import static cn.jdblg.tank.TankFrame.GAME_WIDTH;

/**
 * @author Jadon
 * @create 2020-08-17-10:53
 */
public class Bullet {
    private int x, y;
    private Dir dir;

    public static final int SPEED = 6;
    private Group group;

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

    public static int getSPEED() {
        return SPEED;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    private boolean live = true;

    public Bullet() {
    }

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
    }

    public void paint(Graphics g) {
        switch (dir) {
            case L:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case U:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case R:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case D:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }


        move();
    }

    private void move() {
        switch (dir) {
            case L:
                x -= SPEED;
                break;
            case U:
                y -= SPEED;
                break;
            case R:
                x += SPEED;
                break;
            case D:
                y += SPEED;
                break;
        }
        boundsCheck();
    }

    public void collidesWithTank(Tank tank) {
        if (!tank.isLive() || !this.isLive()) return;
        if (this.group == tank.getGroup()) return;
        Rectangle rect = new Rectangle(getX(), getY(), ResourceMgr.bulletU.getWidth(), ResourceMgr.bulletU.getHeight());
        Rectangle rectTank = new Rectangle(tank.getX(), tank.getY(),
                ResourceMgr.goodTankU.getWidth(), ResourceMgr.goodTankU.getHeight());
        if(rect.intersects(rectTank)){
            this.die();
            tank.die();
        }
    }

    private void boundsCheck() {
        if (x < 0 || y < 30 || x > GAME_WIDTH || y > GAME_HEIGHT) {
            live = false;
        }
    }

    public void die(){
        this.setLive(false);
    }

}
