package JavaSE.ConCurrent.Synchronize;

/**
 * @Description：Demo5 反例
 *      虽然我们使用synchronized修饰了increase方法，
 *      但却new了两个不同的实例对象，这也就意味着存在着
 *      两个不同的实例对象锁，因此t1和t2都会进入各自的
 *      对象锁，也就是说t1和t2线程使用的是不同的锁，因
 *      此线程安全是无法保证的。
 *
 * @Author: Krest
 * @Date: 2021/9/5 11:42
 */
public class Demo5Anti implements Runnable{
    static int i=0;

    public synchronized void increase(){
        i++;
    }
    @Override
    public void run() {
        for(int j=0;j<1000000;j++){
            increase();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        //new新实例
        Thread t1=new Thread(new Demo5Anti());
        //new新实例
        Thread t2=new Thread(new Demo5Anti());
        t1.start();
        t2.start();
        //join含义:当前线程A等待thread线程终止之后才能从thread.join()返回
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
