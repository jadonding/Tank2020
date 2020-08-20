package cn.jdblg.tank;


/**
 * @author Jadon
 * @create 2020-08-16-20:59
 */

public class Main {
    public static void main(String[] args) {
        TankFrame tf = TankFrame.INSTANCE;
//        Audio audio = new Audio("audio/war1.wav");
//        Thread t1 = new Thread(() -> audio.play());
//        if(tf.gm.getMyTank().isLive()){
//            t1.start();
//        }else {
//            t1.stop();
//            audio.close();
//        }
        new Thread(() -> new Audio("audio/war1.wav").loop()).start();
        if(!(tf.gm.getMyTank().isLive())){
            tf.setVisible(false);
        }else tf.setVisible(true);
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