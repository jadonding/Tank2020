package cn.jdblg.tank.chainofresponsibility;

import cn.jdblg.tank.GameObject;
import cn.jdblg.tank.Tank;

/**
 * @author Jadon
 * @create 2020-08-19-17:53
 */
public class TankTankCollider implements Collider {

    @Override
    public boolean collide(GameObject gameObject1, GameObject gameObject2) {
        if (gameObject1 != gameObject2 && gameObject1 instanceof Tank && gameObject2 instanceof Tank) {
            Tank tank1 = (Tank) gameObject1;
            Tank tank2 = (Tank) gameObject2;
            if (tank1.isLive() && tank2.isLive()) {
                if (tank1.getRect().intersects(tank2.getRect())) {
                    tank1.back();
                    tank2.back();
                }
            }
        }
        return true;
    }
}
