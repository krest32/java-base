package com.krest.xml.utils;

public class OrderNumberCreator {

    private static int sn = 0;

    private OrderNumberCreator() {
    }

    public static synchronized String nextOrderNumber() {
        if (sn == 9999) {
            sn = 0;
        } else {
            sn++;
        }
        return DateUtil.ToString("yyyyMMddHHmmss") + padLeft(sn);
    }

    private static String padLeft(int sn) {
        String str = String.valueOf(sn);
        int length = str.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < (4 - length); i++) {
            sb.append("0");
        }
        return sb.toString() + str;
    }
}
