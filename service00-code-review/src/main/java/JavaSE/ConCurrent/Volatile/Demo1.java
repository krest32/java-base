package JavaSE.ConCurrent.Volatile;

/**
 * volatile不保证原子性
 * inc++不是一个原子性操作，可以由读取、加、赋值3步组成，所以结果并不一定能达到20000。.
 */
public class Demo1 {
    public volatile static int inc = 0 ;

    public static void increase (){
        inc++;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    increase();
                }
            }).start();
        }
        while(Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+" "+ inc);
    }
}

