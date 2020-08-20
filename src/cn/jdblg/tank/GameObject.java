package cn.jdblg.tank;

import java.awt.*;
import java.io.Serializable;

/**
 * @author Jadon
 * @create 2020-08-19-16:35
 */
public abstract class GameObject implements Serializable {

    public abstract void paint(Graphics g);
    public abstract boolean isLive();
}
