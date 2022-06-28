package JavaSE.ConCurrent.Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description：Lock的使用如下
 * Author: Krest
 * Date: 2021/9/5 14:22
 */
public class Demo {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        if(lock.tryLock()) {
            try{
                //处理任务
            }catch(Exception ex){

            }finally{
                lock.unlock();   //释放锁
            }
        }else {
            //如果不能获取锁，则直接做其他事情
        }

    }
}
