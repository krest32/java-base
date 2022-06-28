package com.krest.utils.util;

import java.text.NumberFormat;


public class ExcelUtil {
    public static void main(String[] args) throws Exception {
        System.out.print("Progress:");
        NumberFormat num = NumberFormat.getPercentInstance();
        num.setMaximumIntegerDigits(4);
        num.setMaximumFractionDigits(3);

        for (int i = 1; i <= 13; i++) {
            double percent = i / 13.0;
            String temp = num.format(percent);
            System.out.print(temp);
            Thread.sleep(1000);
            // 退格
            if (i != 13) {
                for (int j = 0; j < temp.length(); j++) {
                    System.out.print("\b");
                }
            }
        }
        System.out.println();
    }
}
