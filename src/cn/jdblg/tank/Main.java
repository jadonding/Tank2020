package cn.jdblg.tank;
import java.awt.*;
import java.util.concurrent.TimeUnit;
/**
 * @author Jadon
 * @create 2020-08-16-20:59
 */


public class Main {
    public static void main(String[] args) {
        TankFrame tf = new TankFrame();

        tf.setVisible(true);

        for(;;) {
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            tf.repaint();
        }
    }
}