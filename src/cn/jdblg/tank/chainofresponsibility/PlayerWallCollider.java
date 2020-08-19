package cn.jdblg.tank.chainofresponsibility;

import cn.jdblg.tank.*;

/**
 * @author Jadon
 * @create 2020-08-19-17:53
 */
public class PlayerWallCollider implements Collider{

    @Override
    public boolean collide(GameObject gameObject1, GameObject gameObject2) {
        if (gameObject1 instanceof Player && gameObject2 instanceof Wall){
            Player player = (Player) gameObject1;
            Wall wall = (Wall) gameObject2;
            if (!player.isLive()) return false;
            if(player.getRect().intersects(wall.getRect())){
                player.back();
                return false;
            }
        }else if(gameObject2 instanceof Player && gameObject1 instanceof Wall){
            collide(gameObject2,gameObject1);
        }return true;
    }
}
