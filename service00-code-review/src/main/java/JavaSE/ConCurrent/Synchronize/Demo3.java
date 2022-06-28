package JavaSE.ConCurrent.Synchronize;

/**
 * 使用if语句控制一个资源，可能会出现超卖的情况
 */
public class Demo3 {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        },"C").start();

    }
}

class Ticket{
    private int number = 50 ;
    private int sales = 1;
    public void sale (){
        if ( number != 0){
            number--;
            System.out.println(Thread.currentThread().getName()+"卖出了"+sales+"票，剩余："+number);
        }
    }
}
