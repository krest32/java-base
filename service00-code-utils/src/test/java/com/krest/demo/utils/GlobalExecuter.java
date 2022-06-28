package com.krest.demo.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Data
public class GlobalExecuter {
    /**
     * 任务队列
     */
    public static LinkedBlockingDeque<Runnable> taskQueue;

    /**
     * 线程池
     */
    public static ExecutorService threadPoolExecutor;

    /**
     * 协助判断，是否线程池的任务全部结束
     */
    private static volatile AtomicLong count = new AtomicLong(0);

    /**
     * 调度器的执行状态
     */
    private volatile boolean status = false;

    public GlobalExecuter(int queueSize,
                          int nThrends) {
        this.taskQueue = new LinkedBlockingDeque<>(queueSize);
        threadPoolExecutor = new ThreadPoolExecutor(10,
                Integer.valueOf(nThrends), 5, TimeUnit.MINUTES,
                taskQueue,
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

    }

    public Long getCount() {
        return (long) taskQueue.size();
    }

    public boolean isStatus() {
        return status;
    }

    private AtomicLong countTask() {
        int sum = this.taskQueue.size();
        return new AtomicLong(sum);
    }

    /**
     * 执行方法
     */
    public void run() {
        while (!taskQueue.isEmpty()) {
            Runnable task = taskQueue.poll();
            threadPoolExecutor.execute(() -> {
                task.run();
            });
            System.out.println(count.incrementAndGet());
        }
        // 循环执行执行计划
//        while (!taskQueue.isEmpty()) {
//            try {
//
//                // 执行计划
//                this.loopExecutor.execute(() -> this.taskQueue.poll().taskJob());
//                System.out.println(count.incrementAndGet());
//            } catch (Exception e) {
//                log.error("任务执行中发生异常", e);
//            }
//        }
    }

    /**
     * 追加任务
     *
     * @param task
     */
    public void addTask(Task task) {
        this.taskQueue.offer(task);
    }
}
