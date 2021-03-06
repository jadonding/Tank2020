package cn.jdblg.tank.strategy;

import cn.jdblg.tank.*;

/**
 * @author Jadon
 * @create 2020-08-18-16:28
 */
public class FourDirFireSrategy implements FireStrategy{

    @Override
    public void fire(Player player) {
        int bY = player.getY() + ResourceMgr.goodTankU.getHeight() / 2 - ResourceMgr.bulletU.getHeight() / 2;
        int bX = player.getX() + ResourceMgr.goodTankU.getWidth() / 2 - ResourceMgr.bulletU.getWidth() / 2;
        Dir[] dirs = Dir.values();
        for(Dir d : dirs){
            TankFrame.INSTANCE.gm.getGameObjects().add(new Bullet(bX, bY, d, player.getGroup()));
        }
        new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }
}
