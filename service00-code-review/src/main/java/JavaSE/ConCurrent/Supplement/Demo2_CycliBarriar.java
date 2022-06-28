package JavaSE.ConCurrent.Supplement;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *
 * 内容： CountDownLatch
 * 作用： 并发加法计数
 *
 */
public class Demo2_CycliBarriar {
    public static void main(String[] args) {

        //一共有两个变量，数字7等待执行的数字，后面是执行的结果
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("召唤神龙成功");
        });

        for (int i = 1; i < 8 ; i++) {
            //下面的内容是一个类，只有被final修饰的变量才会被读到
            final int temp = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"收集"+temp+"个龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
