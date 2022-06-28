package JavaSE.ConCurrent.Executor.Executor;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * ScheduledThreadPool 可以定时的或延时的执行任务。
 */
public class ExecutorsDemo3 {
    public static void main(String[] args) {
        //创建线程池
        ScheduledExecutorService schedulePool = Executors.newScheduledThreadPool(1);
        //5秒后执行任务,设置参数
        schedulePool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(Calendar.getInstance().getTime());
                System.out.println("爆炸");
            }
        }, 5, TimeUnit.SECONDS);
        //5秒后执行任务，以后每2秒执行一次
        schedulePool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.print(Calendar.getInstance().getTime());

                System.out.println(" ---爆炸！！");
            }
        }, 5, 2, TimeUnit.SECONDS);

    }

}
