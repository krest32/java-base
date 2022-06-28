package com.krest;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        int[] coins = {1,2,5};
        int target = 5;
        change(target,coins);
    }

    public static int change(int amount, int[] coins) {
        // 动态规划--> 完全背包
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        // 计算的是组合数
//        for (int coin : coins) {
//            // 从最小值coin开始
//            for (int i = coin; i <= amount; i++) {
//                dp[i] += dp[i - coin];
//                System.out.println("i: "+ dp[i]);
//            }
//        }
//         计算的是排列数--> 不符合题意
//         背包容量的每一个值，如：经过 1 和 5 的计算，包含了{1, 5} 和 {5, 1}两种情况
         for (int j = 0; j <= amount; j++) { // 遍历背包容量
             for (int i = 0; i < coins.length; i++) { // 遍历物品
                 if (j - coins[i] >= 0)
                     dp[j] += dp[j - coins[i]];
                 System.out.println(j+ ": " + dp[j]);
             }
         }
        return dp[amount];
    }
}
