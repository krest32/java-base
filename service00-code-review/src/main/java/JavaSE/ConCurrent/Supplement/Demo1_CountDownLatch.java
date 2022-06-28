package JavaSE.ConCurrent.Supplement;


import java.util.concurrent.CountDownLatch;

/**
 *
 * 内容： CountDownLatch
 * 作用： 并发减法计数
 * 应用 countDownLatch.countDown() 每次数量减 1
 * 应用 countDownLatch.await() 等待数量为 0，再继续向下执行
 *
 *
 */
public class Demo1_CountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        //变量是一个倒计时的总数，当有必须要执行的任务时候在使用
        CountDownLatch countDownLatch = new CountDownLatch(7);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"  Go out");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();  //等待计数器归零，然后才能向下执行
        System.out.println("关门");

    }


}
