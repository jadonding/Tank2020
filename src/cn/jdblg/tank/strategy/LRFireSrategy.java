package cn.jdblg.tank.strategy;

import cn.jdblg.tank.*;

/**
 * @author Jadon
 * @create 2020-08-18-16:28
 */
public class LRFireSrategy implements FireStrategy {

    @Override
    public void fire(Player player) {
        int bY = player.getY() + ResourceMgr.goodTankU.getHeight() / 2 - ResourceMgr.bulletU.getHeight() / 2;
        int bX = player.getX() + ResourceMgr.goodTankU.getWidth() / 2 - ResourceMgr.bulletU.getWidth() / 2;

        TankFrame.INSTANCE.add(new Bullet(bX, bY, Dir.L, player.getGroup()));
        TankFrame.INSTANCE.add(new Bullet(bX, bY, Dir.R, player.getGroup()));

        new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }
}
