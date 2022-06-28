package JavaSE.ConCurrent.Synchronize;

/**
 * Description：可重入锁
 * Author: Krest
 * Date: 2021/9/5 11:58
 */
public class Demo9 implements Runnable{
    static Demo9 instance=new Demo9();
    static int i=0;
    static int j=0;
    @Override
    public void run() {
        for(int j=0;j<1000000;j++){

            //this,当前实例对象锁
            synchronized(this){
                i++;
                //synchronized的可重入性
                increase();
            }
        }
    }

    public synchronized void increase(){
        j++;
    }


    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(instance);
        Thread t2=new Thread(instance);
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println(i);
    }
}
