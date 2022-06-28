package JavaSE.ConCurrent.Lock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo1 {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                ticket.sale();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                ticket.sale();
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                ticket.sale();
            }
        },"C").start();

    }
}

/**
 * 公共资源类，线程去操作这个公共资源类；
 */
class Ticket{
    private int number = 80 ;
    private int sales = 1;
    Lock lock = new ReentrantLock();
    public void sale (){

        lock.lock();
        try {
            if ( number> 0){
                number--;
                System.out.println(Thread.currentThread().getName()+"卖出了"+sales+"票，剩余："+number);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}


