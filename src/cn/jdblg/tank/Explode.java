package cn.jdblg.tank;

/**
 * @author Jadon
 * @create 2020-08-16-21:00
 */

import java.awt.*;
public class Explode {
    private int x, y;
    private int width, height;
    private boolean live = true;
    private int step = 0;
//    public int getX() {
//        return x;
//    }
//
//    public void setX(int x) {
//        this.x = x;
//    }
//
//    public int getY() {
//        return y;
//    }
//
//    public void setY(int y) {
//        this.y = y;
//    }


    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = ResourceMgr.explodes[0].getWidth();
        this.height = ResourceMgr.explodes[0].getHeight();
    }


    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public void paint(Graphics g) {
        if(!isLive()) return;
        g.drawImage(ResourceMgr.explodes[step],x,y,null);
        step++;
        if(step >= ResourceMgr.explodes.length){
            this.die();
        }
    }

    private void die() {
        new Thread(()->new Audio("audio/explode.wav").play()).start();
        setLive(false);
    }


}
