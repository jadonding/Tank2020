package cn.jdblg.tank.chainofresponsibility;

import cn.jdblg.tank.GameObject;

import java.io.Serializable;

/**
 * @author Jadon
 * @create 2020-08-20-2:38
 */
public interface Collider extends Serializable {
    public boolean collide(GameObject gameObject1, GameObject gameObject2);
}
