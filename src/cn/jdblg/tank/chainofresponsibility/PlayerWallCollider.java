package cn.jdblg.tank.chainofresponsibility;

import cn.jdblg.tank.*;

/**
 * @author Jadon
 * @create 2020-08-19-17:53
 */
public class PlayerWallCollider implements Collider {

    @Override
    public boolean collide(GameObject gameObject1, GameObject gameObject2) {
        if (gameObject1 instanceof Player && gameObject2 instanceof Wall) {
            Player player = (Player) gameObject1;
            Wall wall = (Wall) gameObject2;

            if (player.getRect().intersects(wall.getRect())){
                player.back();
//                if ((player.getX() + player.getW() >= wall.getX())
//                        && (player.getX() + player.getW() <= wall.getX() + wall.getW())
//                        &&(player.getDir() == Dir.R)) {
//                    player.setrStop(true);
//                }else if((player.getY() + player.getH() >= wall.getY())
//                        && (player.getY() + player.getH() <= wall.getY() + wall.getW())
//                        &&(player.getDir() == Dir.D)){
//                    player.setdStop(true);
//                }else if ((player.getX() <= wall.getX() + wall.getW())
//                        && (player.getX() >= wall.getX())
//                        &&(player.getDir() == Dir.L)) {
//                    player.setlStop(true);
//                }else if((player.getY() <= wall.getY() + wall.getH())
//                        && (player.getY() >= wall.getY())
//                        && (player.getDir() == Dir.U)) {
//                    player.setuStop(true);
//                }
                if(player.getDir() == Dir.D) {
                    player.setdStop(true);
                }
                else if(player.getDir() == Dir.U) {
                    player.setuStop(true);
                }
                else if(player.getDir() == Dir.L) {
                    player.setlStop(true);
                }
                else if(player.getDir() == Dir.R) {
                    player.setrStop(true);
                }
            }else {
                player.setrStop(false);
                player.setlStop(false);
                player.setuStop(false);
                player.setdStop(false);
            }
        }
//        else if (gameObject1 instanceof Wall && gameObject2 instanceof Player) {
//            collide(gameObject2, gameObject1);
//        }
        return true;
    }
}



