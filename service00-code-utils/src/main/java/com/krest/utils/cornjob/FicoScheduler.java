package com.krest.utils.cornjob;

import lombok.extern.slf4j.Slf4j;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import java.util.UUID;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Slf4j
public class FicoScheduler {

    /**
     * 每小时执行一次任务
     * @param HoursJob
     * @throws Exception
     */
    public static void HoursJob(Class HoursJob) throws Exception{
            // 首先，从 SchedulerFactory 中取得一个 Scheduler 的引用
            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler scheduler = sf.getScheduler();
            //jobs可以在scheduled的sched.start()方法前被调用d
            //将 job 通过 JobDetail 传给 Scheduler
            JobDetail job = newJob(HoursJob)
                    .withIdentity("Hours-"+HoursJob.getName()+"-job",
                            "group-"+HoursJob.getName()+"job")
                    .build();

            //设置触发方式 这里用到的是 cron 表达式
            CronTrigger trigger = newTrigger()
                    .withIdentity("cron-"+HoursJob.getName()+"trigger",
                            "cron-"+HoursJob.getName()+"group")
                    // 每小时执行一次
                    .withSchedule(cronSchedule("0 0 * * * ? *"))
                    .build();

            scheduler.scheduleJob(job, trigger);
            // 计时器开始执行
            scheduler.start();
    }

    /**
     * 执行小时并执行任务
     * @param HoursJob 需要继承Job类，重写方法
     * @param AppointedHour 输入任务运行的指定时间
     * @throws Exception
     */
    public static void AppointedJob(Class HoursJob,String AppointedHour,String AppointedMinutes) throws Exception{
        if(AppointedHour.isEmpty()
                || !isHourStr(AppointedHour)
                || AppointedHour.length()>2 ){
            throw new Exception("input hours error");
        }
        log.info("job start at everyday : " +AppointedHour +":"+AppointedMinutes );

        String cornStr = "0 "+ AppointedMinutes+ " "+ AppointedHour+" * * ? *";
        // 首先，从 SchedulerFactory 中取得一个 Scheduler 的引用
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = sf.getScheduler();
        //jobs可以在scheduled的sched.start()方法前被调用d

        //将 job 通过 JobDetail 传给 Scheduler
        JobDetail job = newJob(HoursJob)
                .withIdentity(HoursJob.getSimpleName()+"_"+ UUID.randomUUID() +"_"+AppointedHour+"-job",
                        "group-"+HoursJob.getName()+AppointedHour+"-job")
                .build();

        //设置触发方式 这里用到的是 cron 表达式
        CronTrigger trigger = newTrigger()
                .withIdentity("cron-"+HoursJob.getSimpleName()+"_"+ UUID.randomUUID() +"_"+AppointedHour+"-trigger",
                        "cron-"+HoursJob.getName()+AppointedHour+"-group")
                // 每小时执行一次
                .withSchedule(cronSchedule(cornStr))
                .build();

        scheduler.scheduleJob(job, trigger);
        // 计时器开始执行
        scheduler.start();
    }


    /**
     * 根据传入的Corn表达式，指定时间去执行任务
     * @param HoursJob 传入的任务
     * @param cornStr corn表达式
     * @throws Exception
     */
    public static void CornJob(Class HoursJob,String cornStr) throws Exception{
        if(cornStr.isEmpty()){
            throw new Exception("input corn string error");
        }
        // 首先，从 SchedulerFactory 中取得一个 Scheduler 的引用
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = sf.getScheduler();
        //jobs可以在scheduled的sched.start()方法前被调用d
        //将 job 通过 JobDetail 传给 Scheduler
        JobDetail job = newJob(HoursJob)
                .withIdentity(HoursJob.getName()+cornStr+"-job",
                        "group-"+HoursJob.getName()+cornStr+"-job")
                .build();

        //设置触发方式 这里用到的是 cron 表达式
        CronTrigger trigger = newTrigger()
                .withIdentity(HoursJob.getName()+"-cron-"+cornStr+"-trigger",
                        HoursJob.getName()+"-cron-"+cornStr+"-group")
                // 每小时执行一次
                .withSchedule(cronSchedule(cornStr))
                .build();
        scheduler.scheduleJob(job, trigger);
        // 计时器开始执行
        scheduler.start();
    }


    /**
     * 判断String 能否转转化为 0-24 整数
     * @param str
     * @return
     */
    private static boolean isHourStr(String str){
        try{
            Integer integer = Integer.valueOf(str);
            if(integer>=0 && integer<=24){
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }
}
