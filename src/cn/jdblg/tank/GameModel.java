package cn.jdblg.tank;

import cn.jdblg.tank.chainofresponsibility.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jadon
 * @create 2020-08-20-1:42
 */
public class GameModel {
    public Player getMyTank() {
        return myTank;
    }
    private Player myTank;

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    List<GameObject> gameObjects;
    ColliderChain chain = new ColliderChain();

    public GameModel() {
        initGameObjs();
    }

    private void initGameObjs() {
        myTank = new Player(100, 100, Dir.R, Group.GOOD);
        gameObjects = new ArrayList<>();
        int initTankCount = Integer.parseInt(PropertyMgr.get("initTankCount"));
        for (int i = 0; i < initTankCount; i++) {
            gameObjects.add(new Tank(50 + 70 * i, 400, Dir.D, Group.BAD));
        }
        gameObjects.add(new Wall(500,100,80,200));
        gameObjects.add(myTank);
    }
//    public void add(GameObject gameObject) {
//        gameObjects.add(gameObject);
//    }
    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("gameObjects:" + gameObjects.size(), 10, 50);
        g.setColor(c);
        myTank.paint(g);
        myTank.move();

        for (int i = 0; i < gameObjects.size(); i++) {
            if(!gameObjects.get(i).isLive()){
                gameObjects.remove(i);
                continue;
            }
            GameObject g1 = gameObjects.get(i);
            for(int j = 0; j < gameObjects.size(); j++){
                GameObject g2 = gameObjects.get(j);
                chain.collide(g1,g2);
            }
            if(gameObjects.get(i).isLive()) {
                gameObjects.get(i).paint(g);
            }

        }
    }
}
