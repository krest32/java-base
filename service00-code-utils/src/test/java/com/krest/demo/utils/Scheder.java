package com.krest.demo.utils;

import lombok.extern.slf4j.Slf4j;
import cn.hutool.core.util.RandomUtil;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Scheder {
    /**
     * 任务信息数组
     */
    private TaskInfo[] taskInfos;

    /**
     * 执行计划队列
     */
    private LinkedBlockingQueue<Plan> planQueue;


    /**
     * 允许并行执行的线程数
     */
    private ExecutorService loopExecutor;

    /**
     * 执行模式：
     * 1：所有任务信息都执行
     * 2：先执行部分任务，执行完后再执行其他任务
     */
    private int model;

    /**
     * 先执行的数量
     */
    private int modelSize;

    /**
     * model = 2 时有效
     */
    private ArrayList<Integer> indexList = new ArrayList<>();

    /**
     * 协助判断，是否线程池的任务全部结束
     */
    private AtomicInteger count;

    /**
     * 调度器的执行状态
     */
    private volatile boolean status = false;


    /**
     * 构造器
     *
     * @param taskInfos 任务数组
     * @param nThrends  同时执行任务中的计划线程数
     * @param queueSize 计划执行队列
     * @param model     执行模式 1：所有任务信息都执行 2：先执行部分任务，执行完后再执行其他任务
     * @param modelSize 每批执行任务的数量
     */
    public Scheder(TaskInfo[] taskInfos, int nThrends, int queueSize, int model, Integer modelSize) {
        this.taskInfos = taskInfos;
        this.planQueue = new LinkedBlockingQueue<>(queueSize);
        this.loopExecutor = Executors.newFixedThreadPool(nThrends);
        this.model = model;
        if (this.model == 2 && modelSize != null) {
            this.modelSize = modelSize > taskInfos.length ? taskInfos.length : modelSize;
        } else {
            this.modelSize = taskInfos.length;
        }
        count = countPlan();
    }

    /**
     * 计算一共有多少执行计划
     *
     * @return /
     */
    private AtomicInteger countPlan() {
        int sum = 0;
        for (int i = 0; i < this.taskInfos.length; i++) {
            sum += this.taskInfos[i].getPlanQueue().size();
        }
        return new AtomicInteger(sum);
    }

    public Scheder(TaskInfo[] taskInfos, int nThrends, int model, Integer modelSize) {
        this(taskInfos, nThrends, 100, model, modelSize);
    }

    public Scheder(TaskInfo[] taskInfos, int nThrends) {
        this(taskInfos, nThrends, 100, 1, null);
    }

    public Scheder(TaskInfo[] taskInfos) {
        this(taskInfos, 10, 100, 1, null);
    }

    public void setModel(int model) {
        this.model = model;
    }

    public int getModel() {
        return model;
    }

    public void setModelSize(int modelSize) {
        this.modelSize = modelSize;
    }

    public int getModelSize() {
        return modelSize;
    }

    public ArrayList<Integer> getIndexList() {
        return indexList;
    }


    public AtomicInteger getCount() {
        return count;
    }

    public boolean isStatus() {
        return status;
    }

    /**
     * 执行方法
     */
    public void run() {
        if (this.status) {
            log.warn("任务处于启动状态");
            return;
        }
        this.status = true;
        // 开启向队列中添加执行计划线程
        init();
        // 循环执行执行计划
        while (this.status) {
            // 所有执行计划执行完后，退出
            if (this.taskInfos.length <= 0 && this.planQueue.size() == 0) {
                this.status = false;
                break;
            }
            try {
                // 获取一个执行计划
                Plan plan = this.planQueue.take();
                // 执行计划
                this.loopExecutor.execute(() -> plan.run0(this.count));
            } catch (InterruptedException e) {
                log.error("任务执行中发生异常", e);
            }
        }
        int size;
        // 所有线程执行完毕出循环
        for (; ; ) {
            size = this.count.get();
            if (size == 0) {
                break;
            }
        }

        //停止线程池
        this.loopExecutor.shutdown();
        for (; ; ) {
            //只有当线程池中所有线程完成任务时才会返回true，并且需要先调用线程池的shutdown方法或者shutdownNow方法。
            if (this.loopExecutor.isTerminated()) {
                System.out.println("执行结束！");
                break;
            }
        }
    }

    /**
     * 开启一个线程，持续向执行计划队列添加执行计划，直到所有的计划任务添加完
     */
    private void init() {
        new Thread(() -> {
            while (this.status) {
                // 任务信息数组数量
                int length = this.taskInfos.length;
                // 执行完结束线程
                if (length <= 0) {
                    break;
                }
                // 获取添加执行计划的的任务索引值
                int index = getIndexOfModel(this.model, length);
                TaskInfo taskInfo = null;
                try {
                    taskInfo = this.taskInfos[index];

                } catch (Exception e) {
                    e.printStackTrace();
                }

                LinkedList<Plan> plans = taskInfo.getPlanQueue();
                if (plans.size() > 0) {
                    Plan plan = plans.removeFirst();
                    try {
                        this.planQueue.put(plan);
                    } catch (InterruptedException e) {
                        log.error("向执行计划队列放入计划异常", e);
                    }
                } else {
                    this.taskInfos = reBuildTaskInfos(this.taskInfos, index);
                }
            }
        }).start();
    }

    /**
     * 根据执行模式获取添加执行计划的的任务信息索引值
     *
     * @param model  执行模式
     * @param length 任务信息数组数量
     * @return 任务信息索引值
     */
    private int getIndexOfModel(int model, int length) {
        if (model == 1) {
            return RandomUtil.randomInt(0, length * 100) % length;
        } else {
            this.indexList.removeIf(item -> item >= length);
            if (this.indexList.size() < this.modelSize) {
                int index = RandomUtil.randomInt(0, length * 100) % length;
                this.indexList.add(index);
                return index;
            } else {
                return this.indexList.get(RandomUtil.randomInt(0, length * 100) % this.indexList.size());
            }
        }
    }

    /**
     * 重新构建任务信息数组
     *
     * @param taskInfos 原来任务信息数组
     * @param index     需要移除的任务信息
     * @return 新的任务信息数组
     */
    private TaskInfo[] reBuildTaskInfos(TaskInfo[] taskInfos, int index) {
        TaskInfo[] newTaskINfo = new TaskInfo[taskInfos.length - 1];
        for (int j = 0, i = 0; i < taskInfos.length; i++) {
            if (i != index) {
                newTaskINfo[j] = taskInfos[i];
                j++;
            }
        }
        return newTaskINfo;
    }
}
