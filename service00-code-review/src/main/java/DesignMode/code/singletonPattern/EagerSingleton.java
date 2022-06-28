package DesignMode.code.singletonPattern;

/**
 * @author: krest
 * @date: 2021/4/25 17:01
 * @description: 单例模式——饿汉式
 */
public class EagerSingleton {

    private static EagerSingleton instance = new EagerSingleton();

    private EagerSingleton(){};

    public static EagerSingleton getInstance() {
        return instance;
    }
}
