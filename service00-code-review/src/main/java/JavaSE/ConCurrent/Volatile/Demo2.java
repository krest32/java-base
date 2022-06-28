package JavaSE.ConCurrent.Volatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * volatile + Atomic院子类的包装类实现了
 *              同步功能加锁的功能
 *
 * 底层使用CAS，效率比Synch效率高很多
 *
 */
public class Demo2 {

//    public volatile static AtomicInteger inc = new AtomicInteger(128) ;
    public volatile static AtomicInteger inc = new AtomicInteger(0) ;

    public static void increase (){
        inc.getAndIncrement();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    increase();
                }
            }).start();
        }
        while(Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+" "+ inc);
    }
}

