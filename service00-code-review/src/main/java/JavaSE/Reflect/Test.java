package JavaSE.Reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Test {

    public static void main(String[] args) throws Exception {

        /*
         *通过反射获取实例
         */
        Class<?> aClass = Class.forName("JavaSE.Reflect.HelloWorld");
        HelloWorld helloWorld = (HelloWorld) aClass.newInstance();

        /*
         *获取类属性信息
         */
        Class<?> aClass1 = Class.forName("JavaSE.Reflect.HelloWorld");
        //获取属性信息
        Field[] declaredFields = aClass.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field fields = declaredFields[i];
            //不管成员变量的访问权限是public、protected、默认、还是private,isAccessible()方法都返回false
            //当isAccessible()的结果是false时，如果该字段是private修饰的不允许通过反射访问该字段的值 ，必须要改成true才可以访问 所以     f.setAccessible(true);作用就是让我们在用反射时访问私有变量
            //设置私有属性是否可以访问
            if (fields.isAccessible()) {
                fields.setAccessible(false);
            }
            System.out.println("属性名为" +fields.getName());
            System.out.println("属性类型为"+fields.getType());
            System.out.println("属性值为"+fields.get(aClass1.newInstance()));
            System.out.println("访问修饰符为"+ Modifier.toString(fields.getModifiers()));
        }

        /*
         *获取类方法信息
         */
        Class<?> aClass2 = Class.forName("JavaSE.Reflect.HelloWorld");
        Method[] declaredMethods = aClass2.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println("方法名" + method.getName());
            System.out.println("返回类型" + method.getReturnType());
            System.out.println("访问修饰符" + Modifier.toString(method.getModifiers()));

            //参数类型  class数组
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length > 0) {

                for (Class c : parameterTypes) {

                    System.out.println("参数类型为" + c);
                }
            }
            System.out.println();

        }

        /*
         *获取类的构造函数信息
         */
        Class<?> aClass3 = Class.forName("JavaSE.Reflect.HelloWorld");
        Constructor<?>[] constructors = aClass3.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println("名字"+constructor.getName());
            System.out.println("访问修饰符" + Modifier.toString(constructor.getModifiers()));
            //获取参数类型的  数组
            Class[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length >0) {
                for (Class cla : parameterTypes) {
                    System.out.println("参数类型"+cla);
                }
            }
        }

        /*
         *修改类的属性
         */
        Class<?> aClass4 = Class.forName("JavaSE.Reflect.HelloWorld");
        //getField只获取public修饰属性
        Field i = aClass4.getField("i");
        HelloWorld helloWorld0 = (HelloWorld) aClass4.newInstance();
        i.set(helloWorld0, 5);
        System.out.println(helloWorld0.i);


        /*
         *调用类的方法
         */
        Class<?> aClass5 = Class.forName("JavaSE.Reflect.HelloWorld");
        HelloWorld helloWorld1 = (HelloWorld) aClass5.newInstance();
        Method setI = aClass5.getDeclaredMethod("setI", int.class);//setA参数int
        Method getI = aClass5.getDeclaredMethod("getI", null);//无参数null
        setI.invoke(helloWorld1, 123);
        getI.invoke(helloWorld1);

        /*
         *调用类的构造方法
         */
        //包名.类名
        Class<?> aClass6 = Class.forName("JavaSE.Reflect.HelloWorld");
        //空参构造
        Constructor<?> constructor = aClass6.getConstructor();
        //有参数构造 传参Class<?>... parameterTypes ，三点表示传参是数组
        Class[] c = {int.class};
        Constructor<?> constructor1 = aClass6.getConstructor(c);
        //也可以这样写,比如构造器参数为int和String
        // aClass.getConstructor(int.class,String.class);

        //通过构造器new一个实例
        HelloWorld helloWorld2 = (HelloWorld) constructor.newInstance();
        HelloWorld helloWorld3 = (HelloWorld) constructor1.newInstance(22);
        System.out.println(helloWorld2);
        System.out.println(helloWorld3);

    }
}
