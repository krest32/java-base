package JavaSE.IO;

import JavaSE.IO.entity.Student;
import org.junit.Test;

import java.io.*;

/**
 * @Description：ObjectInputStream 和ObjectOutputStream ，
 * 该流允许读取或写入用户自定义的类，但是要实现这种功能，
 * 被读取和写入的类必须实现Serializable接口，
 * @Author: Krest
 * @Date: 2021/9/4 19:11
 */
public class ObjectInputStreamDemo1 {
    @Test
    public void test() {
        ObjectOutputStream objectwriter = null;
        ObjectInputStream objectreader = null;
        try {

            objectwriter = new ObjectOutputStream(new FileOutputStream("D:/data/student.txt"));

            objectwriter.writeObject(new Student("gg", 22));
            objectwriter.writeObject(new Student("tt", 18));
            objectwriter.writeObject(new Student("rr", 17));

            objectreader = new ObjectInputStream(new FileInputStream("D:/data/student.txt"));
            for (int i = 0; i < 3; i++) {
                System.out.println(objectreader.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            // TODO自动生成的 catch 块
            e.printStackTrace();
        } finally {
            try {
                objectreader.close();
                objectwriter.close();
            } catch (IOException e) {
                // TODO自动生成的 catch 块
                e.printStackTrace();
            }
        }
    }
}
