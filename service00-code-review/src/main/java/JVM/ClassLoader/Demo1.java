package JVM.ClassLoader;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author: krest
 * @date: 2021/9/5 19:18
 * @description: 自定义类加载器
 */
public class Demo1 extends ClassLoader {
    public Demo1() {
    }

    private String rootDir;/*自定义类加载的查找class的路径*/

        /*指定该类加载器会查找的rootDir目录，和父加载器*/
    public Demo1(String rootDir, ClassLoader parent){
            super();
            this.rootDir = rootDir;

        }

        /*指定该类加载器会查找的rootDir目录*/
    public Demo1(String rootDir){
            this.rootDir = rootDir;
        }


    /**
     * 自定义自己的类加载器，如没有要改变类加载顺序的必要的话，则重写findClass方法，因为这个方法是JDK预留了给我们实现的，
     * 否则就需要修改loadClass的实现。
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //<1>.根据类的全路径（包含包名）类名和放置的目录确定类文件的路径
        String className = name.substring(name.lastIndexOf(".")+1)+ ".class";
        String classFile = rootDir + File.separator + className;
        FileInputStream fileInputStream = null;
        byte[] classData = null;
        try {
            //<2>.将class文件读取到字节数组
            fileInputStream = new FileInputStream(new File(classFile));
            classData = new byte[fileInputStream.available()];
            fileInputStream.read(classData,0,classData.length);
            //<3>.将字节数据创建一个class
            return defineClass(name,classData,0,classData.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //<4>如果父类加载器不是自定义的，上面的加载过程没加载成功，则此调用会throw ClassNotFoundException
        return super.findClass(name);
    }

}

