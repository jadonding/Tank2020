import java.io.IOException;
import java.util.Properties;

/**
 * @author Jadon
 * @create 2020-08-18-15:19
 */
public class TestConfig {
    public static void main(String[] args) {
        Properties props = new Properties();
        try {
            props.load(TestConfig.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String str = (String) props.get("initTankCount");
        System.out.println(str);
    }
}
