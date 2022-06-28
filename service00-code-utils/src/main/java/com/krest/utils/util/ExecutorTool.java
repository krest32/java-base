package com.krest.utils.util;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorTool {
    // 默认参数
    private int coreThreadSize = 10;
    private int maxThreadSize = 20;
    private int queueSize = 50000;
    private int keepAlive = 30;

    // 线程池
    ThreadPoolExecutor workExecutor = null;


    /**
     * 构建线程池参数
     */
    public ExecutorTool(int coreSize, int maxThreadSize) {
        this.coreThreadSize = coreSize;
        this.maxThreadSize = maxThreadSize;
    }

    /**
     * @param coreSize      核心线程参数
     * @param maxThreadSize 最大线程参数
     * @param queueSize     队列参数
     */
    public ExecutorTool(int coreSize, int maxThreadSize, int queueSize) {
        this.coreThreadSize = coreSize;
        this.maxThreadSize = maxThreadSize;
        this.queueSize = queueSize;
    }


    /**
     * 构建线程池
     *
     * @return 线程池
     */
    private ThreadPoolExecutor createExecutor() {
        this.workExecutor = new ThreadPoolExecutor(this.coreThreadSize,
                this.maxThreadSize,
                this.keepAlive,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue(this.queueSize),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        return this.workExecutor;
    }

}
