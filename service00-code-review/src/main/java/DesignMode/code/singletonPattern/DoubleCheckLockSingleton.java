package DesignMode.code.singletonPattern;

/**
 * @author: krest
 * @date: 2021/4/25 17:21
 * @description: 双重校验锁, 需要使用 synchronized 和 volatile
 */
public class DoubleCheckLockSingleton {

    private static volatile DoubleCheckLockSingleton singleton = null;

    private DoubleCheckLockSingleton() {
    }

    public static DoubleCheckLockSingleton getSingleton() {
        if (singleton == null) {
            synchronized (DoubleCheckLockSingleton.class) {
                // 由于指令重重排，可能会产生其他问题，所以需要加上Volatile
                singleton = new DoubleCheckLockSingleton();
            }
        }
        return singleton;
    }
}
