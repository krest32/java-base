package JavaSE.ConCurrent.Thread.Methods.Interrupted;

import java.util.concurrent.TimeUnit;

/**
 * Description：除了阻塞中断的情景，我们还可能会遇到处于运行期且非阻塞的状态的线程，
 *              这种情况下，直接调用Thread.interrupt()中断线程是不会得到任响应的，
 *              如下代码，将无法中断非阻塞状态下的线程：
 *              但线程t1并未被中断，因为处于非阻塞状态的线程需要我们手动进行中断检测并结束程序
 * Author: Krest
 * Date: 2021/9/5 12:03
 */
public class Demo2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(){
            @Override
            public void run(){
                while(true){
                    System.out.println("未被中断");
                }
            }
        };
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        t1.interrupt();

        /**
         * 输出结果(无限执行):
         未被中断
         未被中断
         未被中断
         ......
         */
    }
}
