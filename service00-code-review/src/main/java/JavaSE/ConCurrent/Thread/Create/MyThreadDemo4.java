package JavaSE.ConCurrent.Thread.Create;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description：使用线程池执行线程
 * @Author: Krest
 * @Date: 2021/9/4 23:48
 */
public class MyThreadDemo4 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        MyRunnable runnableTest = new MyRunnable();
        for (int i = 0; i < 5; i++) {
            executorService.execute(runnableTest);
        }
        System.out.println("线程任务开始执行");
        executorService.shutdown();
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " run()方法执行中...");
    }
}
