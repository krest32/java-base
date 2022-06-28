package JavaSE.ConCurrent.Thread.Create;

/**
 * @Description：实现Runnable接口开启新线程
 * @Author: Krest
 * @Date: 2021/9/4 23:42
 */
public class MyThreadDemo2 {

    public static void main(String[] args) {
        RunnableDemo myRunnable = new RunnableDemo();
        Thread thread = new Thread(myRunnable);
        thread.start();
        System.out.println(Thread.currentThread().getName() + "main()方法执行结束 ");
    }

}

class RunnableDemo implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "继承Runnable接口");
    }
}