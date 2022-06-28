package JavaSE.Iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

public class IteratorDemo1 {
    public static void main(String[] args) throws Exception {
        IteratorRemoveElement();
        ForRemoveElement();
    }

    private static void ForRemoveElement() throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        for (Integer num : list) {
            if (num == 1) {
                Runnable t = new Runnable() {
                    @Override
                    public void run() {
                        list.remove(num);
                        System.out.println(Thread.currentThread() + " " + list.size());
                    }
                };

                Thread thread = new Thread(t);
                thread.start();
            }
        }
        Thread.sleep(1000);
        System.out.println(Thread.currentThread() + " " + list.size());

    }

    /**
     * 使用 iterator 移除元素
     */
    private static void IteratorRemoveElement() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next == 1) {
                iterator.remove();
            }
        }
        System.out.println(list.size());
    }
}
