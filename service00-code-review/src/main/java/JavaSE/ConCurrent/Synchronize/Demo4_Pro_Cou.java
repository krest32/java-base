package JavaSE.ConCurrent.Synchronize;

/**
 * 经典:   生产者与消费者 问题
 * 原理：  线程之间的通信
 * 注意：公共资源类的判断不能够使用iF，它会存在虚假唤醒，只能够使用 While（true）
 *
 *
 */
public class Demo4_Pro_Cou {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(()->{
            for (int i = 0; i < 30 ; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 30 ; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
        new Thread(()->{
            for (int i = 0; i < 30 ; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 30 ; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
    }

}

/**
 * 公共资源类，防止被虚假唤醒
 */
class Data{

    private int number = 0;
    public synchronized void increment() throws InterruptedException {
        //线程一多就会存在虚假唤醒
//        if (number!=0){
//            //进入到等待状态
//            this.wait();
//        }
        while (number!=0){
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"=>"+number);
        this.notifyAll();
    }
    public synchronized  void decrement() throws InterruptedException {
//        if(number==0){   //线程一多就会存在虚假唤醒
//            //等待
//            this.wait();
//        }
        while (number==0){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"=>"+number);
        this.notifyAll();
    }
}

