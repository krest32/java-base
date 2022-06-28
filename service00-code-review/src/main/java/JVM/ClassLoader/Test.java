package JVM.ClassLoader;

import java.lang.reflect.Method;

/**
 * @author: krest
 * @date: 2021/9/5 19:22
 * @description:
 */
public class Test {
    /***
     *     定义了一个目录存放class文件，这个其实可以修改为可配置参数，但是需要将IDE生成的Class文件放置过去
     */
    private static final String rootDir = "D:/class/";

    public static void main(String[] args) throws Exception {
        /**
         *  从指定的目录下查找对应的class文件，进行加载，然后创建该对象，
         *  如果加载存在则加载成功，则类加载器应为MyClassLoader
         */
        Demo1 classLoader = new Demo1(rootDir);
        // 包名只能使用小写的形式
        Class c = classLoader.loadClass("entity.People");
        Object object = c.newInstance();
        Method getNameMethod = c.getMethod("getName");
        Method getAgeMethod = c.getMethod("getAge");
        System.out.println("name:" + getNameMethod.invoke(object) + ",age:" + getAgeMethod.invoke(object));
        System.out.println("类加载器为：" + object.getClass().getClassLoader());
    }

}
