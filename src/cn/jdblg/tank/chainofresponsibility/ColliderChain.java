package cn.jdblg.tank.chainofresponsibility;

import cn.jdblg.tank.GameObject;
import cn.jdblg.tank.PropertyMgr;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jadon
 * @create 2020-08-19-19:56
 */
public class ColliderChain {
    private List<Collider> colliders;

    public ColliderChain() {
        initCollider();
    }

    private void initCollider() {
        colliders = new ArrayList<>();
        String[] colliderNames = PropertyMgr.get("colliders").split(",");
        for(String name : colliderNames){
            try {
                Class clazz = Class.forName("cn.jdblg.tank.chainofresponsibility." + name);
                Collider collider = (Collider)(clazz.getDeclaredConstructor().newInstance());
                colliders.add(collider);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public boolean collide(GameObject g1, GameObject g2){
        for(Collider collider : colliders){
            if(!collider.collide(g1, g2))return false;
        }
        return true;
    }

}
