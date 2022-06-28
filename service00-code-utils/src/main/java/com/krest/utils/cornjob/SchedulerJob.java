package com.krest.utils.cornjob;


import org.quartz.Job;
import org.quartz.JobExecutionContext;


public class SchedulerJob extends Thread implements Job {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+" -> SchedulerJob : 执行定时任务......");
	}

	/**
	 * 重写定时任务
	 * @param jobExecutionContext
	 */
	@Override
	public void execute(JobExecutionContext jobExecutionContext)  {
		new SchedulerJob().start();
	}
}
