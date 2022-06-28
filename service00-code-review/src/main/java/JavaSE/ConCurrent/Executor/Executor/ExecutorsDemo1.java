package JavaSE.ConCurrent.Executor.Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 建立线程池只有3条线程，
 * 当任务多的时候，就要进入到等待状态，线程空闲时候，再去运行
 */
public class ExecutorsDemo1 {

    public static void main(String[] args) {
        //创建线程池，容量是3
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " : 执行 Ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
