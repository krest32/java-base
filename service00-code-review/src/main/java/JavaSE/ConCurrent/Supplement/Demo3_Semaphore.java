package JavaSE.ConCurrent.Supplement;


import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


/**
 * Semaphore
 * 作用：是有限的资源被有序的得到，进行限流
 *
 * semaphore.acquire(); 获取资源，假设资源已满，就等待
 * semaphore.release(); 释放资源
 *
 */
public class Demo3_Semaphore {
    public static void main(String[] args) {
        //定义资源数量
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i < 6; i++) {
            new Thread(()->{
                //acquire（）
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"得到资源");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("===================================");
                    System.out.println(Thread.currentThread().getName()+"释放资源");
                    System.out.println("===================================");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }

    }
}
