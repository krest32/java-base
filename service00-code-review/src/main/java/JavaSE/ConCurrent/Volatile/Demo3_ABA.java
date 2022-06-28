package JavaSE.ConCurrent.Volatile;

import java.util.concurrent.atomic.AtomicStampedReference;


/**
 *  通过时间Stamp处理ABA问题,
 *  注意Integer的范围在+-127，所以数字不能够太大，否则会报错
 */

public class Demo3_ABA {
    public static void main(String[] args) {
//        AtomicInteger atomicInteger = new AtomicInteger(2020);
        AtomicStampedReference<Integer> atomicInteger = new AtomicStampedReference<>(1,1);

        new Thread(()->{
            int stamp = atomicInteger.getStamp();
            System.out.println("a1 版本号:"+atomicInteger.getStamp());

            System.out.println(atomicInteger.compareAndSet(1, 2, atomicInteger.getStamp(), atomicInteger.getStamp() + 1));
            System.out.println("a2 版本号:"+atomicInteger.getStamp());

            System.out.println(atomicInteger.compareAndSet(2, 1, atomicInteger.getStamp(), atomicInteger.getStamp() + 1));
            System.out.println("a3 版本号:"+atomicInteger.getStamp());
        },"a").start();

    }
}
