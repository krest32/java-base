package JavaSE.ConCurrent.Collection;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 内容：ConCurrentHashMap 并发容器
 *
 * 问题
 * 1. HashMap 多线程添加元素，会报错 ConcurrentModificationException
 *
 * 解决方案：
 * 1. 使用 Hashtable<>()，因为性能问题，仅作为保留类
 * 2. 使用集合工具类 Collections.synchronizedMap(new HashMap<>());
 * 3. 使用ConcurrentHashMap<>();
 *
 * 对比
 * 原理：方案1和2 使用关键字 synchronized；方案3 使用更加细的锁
 * 性能：方案3 性能提高了很多
 *
 */


public class Demo1_ConCurrentHashMap {
    public static void main(String[] args) {
//        Map<String,String> map =new HashMap<>();   //问题点

//        Map<String,String> map = new Hashtable<>();  //解决方案 1
//        Map<String,String> map= Collections.synchronizedMap(new HashMap<>());   //解决方案 2
        Map<String,String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 20 ; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            },String.valueOf(i)).start();
        }

    }
}
