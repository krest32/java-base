package com.krest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

public class TestThread extends Thread {

    public static List<Long> idList = null;
    public static void main(String[] args) throws InterruptedException {
        idList = new ArrayList<Long>();
        final CountDownLatch latch = new CountDownLatch(1);
        for(int i = 0 ; i < 5 ;i ++ ){
            Thread thread = new TestThread(latch,i);
            thread.start();
        }
        Thread.sleep(5000);    //延时2秒
        System.out.println(idList);
        System.out.println("去重前ID数量："+idList.size());
        idList = idList.stream().distinct().collect(Collectors.toList());
        System.out.println("去重后ID数量："+idList.size());
    }
    private CountDownLatch latch;
    private int num;
    public TestThread(CountDownLatch latch,int num) {
        this.latch = latch;
        this.num = num;
    }

    @Override
    public void run() {
        IdWorker idWorker = new IdWorker();
        latch.countDown();
        try {
            latch.await();
            for (int i = 0; i < 5; i++) {
                long id = Long.parseLong(idWorker.nextId());
                idList.add(id);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
