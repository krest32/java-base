package JavaSE.ConCurrent.Thread.Methods.Interrupted;

import java.util.concurrent.TimeUnit;

/**
 * Description：线程中断演示
 *              使用Thread.interrupt()方式中断该线程，
 *              注意此时将会抛出一个InterruptedException的异常，
 *              同时中断状态将会被复位(由中断状态改为非中断状态)
 *
 *              为什么不用Thread.sleep(2000);而是用TimeUnit.SECONDS.sleep(2);
 *              其实原因很简单，前者使用时并没有明确的单位说明，而后者非常明确表达秒的单位，
 *              事实上后者的内部实现最终还是调用了Thread.sleep(2000);
 *              但为了编写的代码语义更清晰，建议使用TimeUnit.SECONDS.sleep(2);的方式，
 *              注意TimeUnit是个枚举类型。

 * Author: Krest
 * Date: 2021/9/5 11:59
 */
public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                //while在try中，通过异常中断就可以退出run循环
                try {
                    while (true) {
                        //当前线程处于阻塞状态，异常必须捕捉处理，无法往外抛出
                        TimeUnit.SECONDS.sleep(2);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Interruted When Sleep");
                    boolean interrupt = this.isInterrupted();
                    //中断状态被复位
                    System.out.println("interrupt:"+interrupt);
                }
            }
        };
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        //中断处于阻塞状态的线程
        /**
         * 输出结果:
         Interruted When Sleep
         interrupt:false
         */
        t1.interrupt();

    }
}
