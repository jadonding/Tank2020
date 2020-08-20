package cn.jdblg.tank.chainofresponsibility;

import cn.jdblg.tank.Bullet;
import cn.jdblg.tank.GameObject;
import cn.jdblg.tank.Tank;


/**
 * @author Jadon
 * @create 2020-08-19-17:53
 */
public class BulletBulletCollider implements Collider{

    @Override
    public boolean collide(GameObject gameObject1, GameObject gameObject2) {
        if (gameObject1 instanceof Bullet && gameObject2 instanceof Bullet){
            Bullet b1 = (Bullet) gameObject1;
            Bullet b2 = (Bullet) gameObject2;
            if (!b1.isLive() || !b2.isLive() || b1.getGroup() == b2.getGroup()) return false;
            if(b1.getRect().intersects(b2.getRect())){
                b1.die();
                b2.die();
                return false;
            }
        }else if(gameObject1 instanceof Bullet && gameObject2 instanceof Bullet){
            collide(gameObject2,gameObject1);
        }return true;
    }
}
