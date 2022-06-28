package com.krest.utils.util;

public class WatchTool {
    static long start = 0;
    static long end = 0;

    public static long start() {
        start = System.currentTimeMillis();
        return start;
    }

    public static String stop() {
        if (start == 0) {
            return "-1";
        } else {
            end = System.currentTimeMillis();
            return ((end - start) * 0.001) + " s";
        }
    }
}
