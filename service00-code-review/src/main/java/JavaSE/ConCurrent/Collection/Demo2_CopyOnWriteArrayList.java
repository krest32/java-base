package JavaSE.ConCurrent.Collection;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 内容：CopyOnWriteArrayList 并发容器
 *
 * 问题
 * 1. 通过ArrayList 多线程添加元素，会报错 ConcurrentModificationException
 *
 * 解决方案：
 * 1. 使用 Vector
 * 2. 使用集合工具类 Collections.synchronizedList(new ArrayList<>());
 * 3. 使用CopyOnWriteArrayList
 *
 * 对比
 * 原理：方案1和2 使用关键字 synchronized；方案3 使用写入时覆盖
 * 性能：方案3 性能会更好，使用了Lock锁
 *
 */


public class Demo2_CopyOnWriteArrayList {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();   //问题集合
//        List<String> list = new Vector<>();    // 解决方案1
//        List<String> list = Collections.synchronizedList(new ArrayList<>());  //解决方案2
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 1; i <20 ; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
