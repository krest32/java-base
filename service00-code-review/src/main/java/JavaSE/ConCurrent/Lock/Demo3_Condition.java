package JavaSE.ConCurrent.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过Lock与Condition实现了精准唤醒
 */
public class Demo3_Condition {
    public static void main(String[] args) {
        Data3 data3 = new Data3();
        new Thread(() -> {
            for (int i = 0; i <10  ; i++) {
                data3.printA();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i <10  ; i++) {
                data3.printB();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i <10  ; i++) {
                data3.printC();
            }
        }, "C").start();

    }
}

//公共资源类，防止被虚假唤醒,使用While循环
class Data3 {
    private int number = 1;

    /**
     * condition 作为单独监视器，实现了精准的唤醒
     */
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void printA() {
        lock.lock();
        try {
            while (number != 1) {
                condition1.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "=>" + "AAAAAA");
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            while (number != 2) {
                condition2.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "=>" + "BBBBBBBB");
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            while (number != 3) {
                condition3.await();
            }
            number=1;
            System.out.println(Thread.currentThread().getName() + "=>" + "CCCCCC");
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}


