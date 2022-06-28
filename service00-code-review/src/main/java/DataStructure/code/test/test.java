package DataStructure.code.test;

import java.util.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: krest
 * @date: 2021/1/8 13:27
 * @description:
 */
public class test {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();

        lock.unlock();
    }
}
