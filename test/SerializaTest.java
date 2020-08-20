import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author Jadon
 * @create 2020-08-20-2:44
 */
public class SerializaTest {
    @Test
    public void testSave(){
        try {
            T t = new T();
            File file = new File("E://test/a.dat");
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(t);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testLoad(){
        try {
            T t = new T();
            File file = new File("E://test/a.dat");
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            T t1 = (T) (ois.readObject());
            Assertions.assertEquals(2,t.m);
            Assertions.assertEquals(4,t.n);
            Assertions.assertEquals(3,t.a.weight);

//            System.out.println(t.a.weight);

            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class T implements Serializable {
    int m = 2;
    transient int n = 4;
    Apple a = new Apple();
}
class Apple implements Serializable{
    int weight = 3;
}
