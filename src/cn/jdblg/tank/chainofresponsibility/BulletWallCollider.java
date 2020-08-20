package cn.jdblg.tank.chainofresponsibility;

import cn.jdblg.tank.*;

/**
 * @author Jadon
 * @create 2020-08-19-17:53
 */
public class BulletWallCollider implements Collider{

    @Override
    public boolean collide(GameObject gameObject1, GameObject gameObject2) {
        if (gameObject1 instanceof Bullet && gameObject2 instanceof Wall){
            Bullet bullet = (Bullet) gameObject1;
            Wall wall = (Wall) gameObject2;
            if (!bullet.isLive()) return false;
            if(bullet.getRect().intersects(wall.getRect())){
                bullet.die();
                return false;
            }
        }else if(gameObject2 instanceof Bullet && gameObject1 instanceof Wall){
            collide(gameObject2,gameObject1);
        }return true;
    }
}
