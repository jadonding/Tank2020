package cn.jdblg.tank.strategy;

import cn.jdblg.tank.Player;

import java.io.Serializable;

/**
 * @author Jadon
 * @create 2020-08-18-16:11
 */
public interface FireStrategy extends Serializable {
     public void fire(Player player);
}
