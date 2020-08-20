package cn.jdblg.tank;

import cn.jdblg.tank.chainofresponsibility.ColliderChain;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static cn.jdblg.tank.TankFrame.GAME_HEIGHT;
import static cn.jdblg.tank.TankFrame.GAME_WIDTH;

/**
 * @author Jadon
 * @create 2020-08-20-1:42
 */
public class GameModel implements Serializable {
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
        myTank = new Player(GAME_WIDTH/2, GAME_HEIGHT-80, Dir.R, Group.GOOD);
        gameObjects = new ArrayList<>();
        int initTankCount = Integer.parseInt(PropertyMgr.get("initTankCount"));
        int initRowWallCount = GAME_WIDTH/160;
        int initColumnWallCount = GAME_HEIGHT/250;
        for (int i = 0; i < initColumnWallCount; i++) {
            for(int j = 0;j < initRowWallCount;j++){
                gameObjects.add(new Wall(80 + 160*j,130 + 250*i,80,150));
            }
        }
        for (int i = 0; i < initTankCount; i++) {
            gameObjects.add(new Tank(10 + 70 * i, 30, Dir.D, Group.BAD));
        }
        gameObjects.add(myTank);
    }
    public void add(GameObject gameObject) {
        gameObjects.add(gameObject);
    }
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
