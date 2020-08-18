package cn.jdblg.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Jadon
 * @create 2020-08-18-15:24
 */
public class PropertyMgr {
    private static Properties props;

    static {
        try {
            props = new Properties();
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String get(String key){
        return (String)props.get(key);
    }
}
