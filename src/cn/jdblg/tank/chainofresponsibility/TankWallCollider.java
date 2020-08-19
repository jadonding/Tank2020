package cn.jdblg.tank.chainofresponsibility;

import cn.jdblg.tank.Bullet;
import cn.jdblg.tank.GameObject;
import cn.jdblg.tank.Tank;
import cn.jdblg.tank.Wall;

/**
 * @author Jadon
 * @create 2020-08-19-17:53
 */
public class TankWallCollider implements Collider{

    @Override
    public boolean collide(GameObject gameObject1, GameObject gameObject2) {
        if (gameObject1 instanceof Tank && gameObject2 instanceof Wall){
            Tank tank = (Tank) gameObject1;
            Wall wall = (Wall) gameObject2;
            if (!tank.isLive()) return false;
            if(tank.getRect().intersects(wall.getRect())){
                tank.back();
                return false;
            }
        }else if(gameObject2 instanceof Tank && gameObject1 instanceof Wall){
            collide(gameObject2,gameObject1);
        }return true;
    }
}
