package cn.jdblg.tank;

import java.util.Random;

/**
 * @author Jadon
 * @create 2020-08-16-21:01
 */
public enum Dir {
    L, U, R, D;

    private static Random random = new Random();
    public static Dir randomDir(){
        return Dir.values()[random.nextInt(Dir.values().length)];
    }
}
