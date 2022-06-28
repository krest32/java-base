package JavaSE.ConCurrent.Synchronize;

/**
 *
 * 同步代码块
 * 多线程访问同一个资源
 * 用synchronized(this) 同步关键字，只有一个线程能够获取到该代码块的内容
 */

public class Demo1 {

    public static void main(String[] args) throws InterruptedException {
        SyncThread s = new SyncThread();
        Thread t1 = new Thread(s);
        Thread t2 = new Thread(s);
        Thread t3 = new Thread(s);

        t1.start();
        t2.start();
        t3.start();
    }
}

class SyncThread implements Runnable {

    private static int count;

    public SyncThread() {
        count = 0;
    }

    @Override
    public  void run() {

        synchronized(this){
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public int getCount() {
        return count;
    }
}
