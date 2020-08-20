package cn.jdblg.tank.chainofresponsibility;

import cn.jdblg.tank.Bullet;
import cn.jdblg.tank.GameObject;
import cn.jdblg.tank.Tank;


/**
 * @author Jadon
 * @create 2020-08-19-17:53
 */
public class BulletTankCollider implements Collider{

    @Override
    public boolean collide(GameObject gameObject1, GameObject gameObject2) {
        if (gameObject1 instanceof Bullet && gameObject2 instanceof Tank){
            Bullet bullet = (Bullet) gameObject1;
            Tank tank = (Tank) gameObject2;
            if (!tank.isLive() || !bullet.isLive() || bullet.getGroup() == tank.getGroup()) return false;
            if(bullet.getRect().intersects(tank.getRect())){
                bullet.die();
                tank.die();
                return false;
            }
        }else if(gameObject1 instanceof Tank && gameObject2 instanceof Bullet){
            collide(gameObject2,gameObject1);
        }return true;
    }
}
