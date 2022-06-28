package DesignMode.code.singletonPattern;

/**
 * @author: krest
 * @date: 2021/4/25 17:01
 * @description: 单例模式——饿汉式
 */
public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton(){};

    public synchronized static LazySingleton getInstance() {
        if (instance==null){
            return instance=new LazySingleton();
        }else {
            return instance;
        }

    }
}
