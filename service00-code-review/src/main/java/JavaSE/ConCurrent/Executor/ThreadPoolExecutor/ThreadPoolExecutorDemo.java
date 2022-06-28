package JavaSE.ConCurrent.Executor.ThreadPoolExecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池定义
 *  1. 7大参数
 *  2. 核心线程的Shutdown
 *  3. 线程池的预热，可以再启动的时候创建空任务进行执行
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                //等待线程参数
                3,
                //最大线程参数
                5,
                3,
                //线程存活时间
                TimeUnit.MINUTES,
                // 等待的阻塞队列容量
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                // AbortPolicy 超出承载会拒绝，报异常
                // CallerRunsPolicy 哪里来的去哪里，让main线程去处理  (常用策略)
                // DiscardPolicy 超出承载不处理，但是不会报异常
                // DiscardOldestPolicy  尝试去执行，会竞争，如果不成功，也会丢弃任务
                new ThreadPoolExecutor.CallerRunsPolicy());
        // 允许核心线程在存货时间之后进行自动销毁
        threadPool.allowsCoreThreadTimeOut();

        int state = 0;
        try{
            for (int i = 0; i <20; i++) {
                int finalI = i;
                state = i;
                threadPool.execute(()->{
                    System.out.println(finalI +" "+ Thread.currentThread().getName()+" : 执行 Ok"+(1/1));
                }
            );
        }}catch (Exception e){
            System.out.println(state);
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
