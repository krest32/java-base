package com.krest.utils.util;

import lombok.extern.slf4j.Slf4j;

import java.text.NumberFormat;

@Slf4j
public class PerCentUtil {
    public static void showRatePerCent(Integer total, Integer rate) {
        System.out.print("Progress:");
        NumberFormat num = NumberFormat.getPercentInstance();
        num.setMaximumIntegerDigits(4);
        num.setMaximumFractionDigits(3);
        for (int i = 1; i <= total; i++) {
            double percent = i / (double)total;
            String temp = num.format(percent);

            System.out.print(temp);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 退格
            if (i != total) {
                for (int j = 0; j < temp.length(); j++) {
                    System.out.print("\b");
                }
            }
        }
    }
}
