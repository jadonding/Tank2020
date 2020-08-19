package cn.jdblg.tank;

import java.awt.*;

/**
 * @author Jadon
 * @create 2020-08-18-23:34
 */
public class Wall extends GameObject{
    private int x,y,w,h;
    private Rectangle rect;
    public Wall(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        rect = new Rectangle(x, y, w, h);
    }

    public Wall() {
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

    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.gray);
        g.fillRect(x,y,w,h);
        g.setColor(c);
    }

    @Override
    public boolean isLive() {
        return true;
    }

    public Rectangle getRect() {
        return rect;
    }
}
