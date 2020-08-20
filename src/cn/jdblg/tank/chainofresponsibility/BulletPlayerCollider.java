package cn.jdblg.tank.chainofresponsibility;

import cn.jdblg.tank.Bullet;
import cn.jdblg.tank.GameObject;
import cn.jdblg.tank.Player;
import cn.jdblg.tank.Tank;


/**
 * @author Jadon
 * @create 2020-08-19-17:53
 */
public class BulletPlayerCollider implements Collider{

    @Override
    public boolean collide(GameObject gameObject1, GameObject gameObject2) {
        if (gameObject1 instanceof Bullet && gameObject2 instanceof Player){
            Bullet bullet = (Bullet) gameObject1;
            Player player = (Player) gameObject2;
            if (!player.isLive() || !bullet.isLive() || bullet.getGroup() == player.getGroup()) return false;
            if(bullet.getRect().intersects(player.getRect())){
                bullet.die();
                player.die();
                return false;
            }
        }else if(gameObject1 instanceof Player && gameObject2 instanceof Bullet){
            collide(gameObject2,gameObject1);
        }return true;
    }
}
