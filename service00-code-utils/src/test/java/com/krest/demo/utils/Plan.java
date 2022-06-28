package com.krest.demo.utils;

import java.util.concurrent.atomic.AtomicInteger;

public interface Plan {
    /**
     * 线程池执行前
     */
    default void before() {
    }

    void run();

    /**
     * 线程池执行后
     */
    default void after() {
    }

    default void run0(AtomicInteger atomicInteger) {
        try {
            before();
            run();
        } finally {
            after();
            atomicInteger.decrementAndGet();
        }
    }
}
