package JavaSE.Reflect;

public class Test2 {
    public static void main(String[] args) throws Exception {
        /*
         *通过反射获取实例
         */
        Class<?> aClass = Class.forName("JavaSE.Reflect.HelloWorld");
        HelloWorld helloWorld = (HelloWorld) aClass.newInstance();
        System.out.println(helloWorld.getI());

    }
}
