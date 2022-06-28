package com.krest.demo.utils;

import lombok.Data;

import java.util.LinkedList;

@Data
public class TaskInfo {
    /**
     * 任务名称
     */
    private String name;

    /**
     * 执行计划队列
     */
    private LinkedList<Plan> planQueue;


    public TaskInfo(String name, LinkedList<Plan> planQueue) {
        this.name = name;
        this.planQueue = planQueue;
    }
}
