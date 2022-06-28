package JavaSE.ConCurrent.Thread.Create;

/**
 * @Description： 继承Thread类
 * @Author: Krest
 * @Date: 2021/9/4 23:40
 */
public class MyThreadDemo {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println(Thread.currentThread().getName() + " main()方法执行结束");
    }
}
class MyThread extends Thread  {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 继承Thread类");
    }
}
