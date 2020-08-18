package cn.jdblg.tank;


/**
 * @author Jadon
 * @create 2020-08-16-20:59
 */

public class Main {
    public static void main(String[] args) {
        TankFrame tf = TankFrame.INSTANCE;
        new Thread(() -> new Audio("audio/war1.wav").loop()).start();
        tf.setVisible(true);
        for (; ; ) {
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            TankFrame.INSTANCE.repaint();
        }
    }
}