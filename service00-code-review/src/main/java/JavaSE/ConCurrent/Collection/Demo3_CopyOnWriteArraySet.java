package JavaSE.ConCurrent.Collection;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;


/**
 * 内容：CopyOnWriteArraySet 并发容器
 *
 * 问题
 * 1. 通过HashSet 多线程添加元素，会报错 ConcurrentModificationException
 *
 * 解决方案：
 *
 * 1. 使用集合工具类 Collections.synchronizedSet(new HashSet<>())
 * 2. 使用CopyOnWriteArraySet
 *
 * 对比
 * 原理：方案1使用关键字 synchronized；方案2 使用写入时覆盖
 * 性能：方案2 性能会更好，使用了Lock锁
 *
 */

public class Demo3_CopyOnWriteArraySet {
    public static void main(String[] args) {
//        Set<String> set = new HashSet();  //问题集合
//        Set<String> set = Collections.synchronizedSet(new HashSet<>());  //解决方案1
        Set<String> set = new CopyOnWriteArraySet<>(); //解决方案2

        for (int i = 1; i <20 ; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
}
