package DesignMode.code.singletonPattern;


import org.junit.Test;

import static java.lang.Thread.currentThread;

/**
 * @author: krest
 * @date: 2021/4/25 17:04
 * @description:
 */
public class test {
    @Test
    public void test1(){
        StaticInnerClassSingleton instance1 = StaticInnerClassSingleton.getInstance();
        StaticInnerClassSingleton instance2 = StaticInnerClassSingleton.getInstance();
        System.out.println(instance1);
        System.out.println(instance2);
    }

    @Test
    public void test2(){
        DoubleCheckLockSingleton singleton1 = DoubleCheckLockSingleton.getSingleton();
        DoubleCheckLockSingleton singleton2 = DoubleCheckLockSingleton.getSingleton();
        System.out.println(singleton1);
        System.out.println(singleton2);
    }

    @Test
    public void test3(){
        System.out.println(currentThread().getName());
        currentThread().interrupt();
        currentThread().stop();
        System.out.println(currentThread().isInterrupted());
    }
}

