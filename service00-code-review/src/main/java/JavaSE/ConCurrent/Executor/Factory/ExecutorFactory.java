package JavaSE.ConCurrent.Executor.Factory;

import java.util.concurrent.*;

public class ExecutorFactory {

    /**
     * 自定义线程池
     * @param coreThreads
     * @param maxThreads
     * @param keepAliveTimeMs
     * @param threadFactory
     * @return
     */
    public static ThreadPoolExecutor newCustomerThreadExecutor(final int coreThreads, final int maxThreads,
                                                               final long keepAliveTimeMs, final ThreadFactory threadFactory) {
        return new ThreadPoolExecutor(coreThreads, maxThreads, keepAliveTimeMs, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(), threadFactory);
    }


    public static ScheduledExecutorService newScheduledExecutorService(final int nThreads,
                                                                       final ThreadFactory threadFactory) {
        return Executors.newScheduledThreadPool(nThreads, threadFactory);
    }

    public static ScheduledExecutorService newSingleScheduledExecutorService(final ThreadFactory threadFactory) {
        return Executors.newScheduledThreadPool(1, threadFactory);
    }

    public static ExecutorService newSingleExecutorService(final ThreadFactory threadFactory) {
        return Executors.newFixedThreadPool(1, threadFactory);
    }

    public static ExecutorService newSingleExecutorService() {
        return Executors.newFixedThreadPool(1);
    }
}
