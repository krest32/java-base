package JavaSE.ConCurrent.Executor.Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//线程池只有一个线程

public class ExecutorsDemo4 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();

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
