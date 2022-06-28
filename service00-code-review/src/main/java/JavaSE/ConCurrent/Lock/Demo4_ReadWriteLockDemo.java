package JavaSE.ConCurrent.Lock;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
public class Demo4_ReadWriteLockDemo {

    public static void main(String[] args) {
        //MyCache myCache = new MyCache();
        MyCacheLock myCache = new MyCacheLock();
        for (int i = 1; i < 5 ; i++) {
            final int temp = i ;
            new Thread(()->{
                myCache.put(temp+"",UUID.randomUUID().toString().substring(0,5));
            },String.valueOf(i)).start();
        }
        for (int i = 1; i < 5 ; i++) {
            final int temp = i ;
            new Thread(()->{
                myCache.get(temp+"");
            },String.valueOf(i)).start();
        }
    }
}

class MyCacheLock{
    private volatile Map<String,Object> map = new HashMap<>();

    private ReadWriteLock lock = new ReentrantReadWriteLock();
    /**
     *   写的时候只能被一个线程控制
     */
    public void put(String key,Object value){
        try{
            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getName()+"写入:"+key);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写完毕入");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.writeLock().unlock();
        }

    }
    //读的时候可以被多个线程控制
    public void get(String key){
        try{
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName()+"读取:"+key);
            map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取Ok");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.readLock().unlock();
        }


    }

}

class MyCache{
    private volatile Map<String,Object> map = new HashMap<>();

    //写的时候只能被一个线程控制
    public void put(String key,Object value){
        System.out.println(Thread.currentThread().getName()+"写入:"+key);
        map.put(key,value);
        System.out.println(Thread.currentThread().getName()+"写完毕入");

    }
    //读的时候可以被多个线程控制
    public void get(String key){
        System.out.println(Thread.currentThread().getName()+"读取:"+key);
        map.get(key);
        System.out.println(Thread.currentThread().getName()+"读取Ok");
    }

}