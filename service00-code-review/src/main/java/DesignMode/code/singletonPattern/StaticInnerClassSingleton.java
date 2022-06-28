package DesignMode.code.singletonPattern;

/**
 * @author: krest
 * @date: 2021/4/25 17:29
 * @description: 静态内部类加载单例模式
 */
public class StaticInnerClassSingleton {
    private StaticInnerClassSingleton(){};

    private static class Instance{
        private static final StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getInstance(){
        return Instance.instance;
    }

}
