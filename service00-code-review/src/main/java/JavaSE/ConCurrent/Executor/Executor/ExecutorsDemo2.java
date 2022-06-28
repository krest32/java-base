package JavaSE.ConCurrent.Executor.Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 随机变化的线程池
 */

public class ExecutorsDemo2 {
    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newCachedThreadPool();
        try{ for (int i = 0; i <10; i++) {
            threadPool.execute(()->{
                System.out.println(Thread.currentThread().getName()+" : 执行 Ok");
            });
        }}catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
