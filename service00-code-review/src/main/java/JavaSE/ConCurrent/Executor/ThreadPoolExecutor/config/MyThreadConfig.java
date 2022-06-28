package JavaSE.ConCurrent.Executor.ThreadPoolExecutor.config;


import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: krest
 * @date: 2021/5/18 18:47
 * @description:
 */
public class MyThreadConfig {
    public static ThreadPoolExecutor threadPoolExecutor(ThreadPoolConfigProperties pool){
        return new ThreadPoolExecutor(
                pool.coreSize,
                pool.maxSize,pool.
                keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
    }
}
