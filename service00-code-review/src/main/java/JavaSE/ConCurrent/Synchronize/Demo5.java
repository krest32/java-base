package JavaSE.ConCurrent.Synchronize;

/**
 * @Description：同步方法
 * @Author: Krest
 * @Date: 2021/9/5 11:40
 */
public class Demo5 implements Runnable{
    /**
     *   共享资源(临界资源)
     */
    static int i=0;

    /**
     * synchronized 修饰实例方法
     */
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
        Demo5 instance=new Demo5();

        Thread t1=new Thread(instance);
        Thread t2=new Thread(instance);
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(i);
    }
    /**
     * 输出结果:
     * 2000000
     */
}
