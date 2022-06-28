package com.krest.utils.util;

import java.util.*;

public class Demo {
    private int i;

    public static void main(String[] args) {
        int[] nums = {4,3,2,3,5,2,1};
        boolean flag = canPartitionKSubsets(nums, 4);
        System.out.println(flag);
    }

    static boolean flag = true;

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        int len = nums.length;
        int max = nums[0];
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            max = Math.max(max, nums[i]);
        }
        int target = sum / k;
        System.out.println(target);
        // 去掉不合法的条件
        if (sum % k != 0 || max > target)
            return false;
        boolean[] dp = new boolean[len];
        boolean[] vis = new boolean[len];
        dfs(nums, target, dp, vis, 0, 0);
        for (int i = 0; i < len; i++) {
            if (dp[i] == false){
                flag = false;
            }
        }
        return flag;
    }

    private static void dfs(int[] nums, int target, boolean[] dp, boolean[] vis, int idx, int k) {
        if (target == 0) {
            dp[k] = true;
        } else {
            for (int i = idx; i < nums.length; i++) {
                if (vis[i]) {
                    continue;
                }
                System.out.println(target + " " + nums[i]);
                if (target - nums[i] >= 0) {
                    vis[i] = true;
                    target -= nums[i];
                    dfs(nums, target, dp, vis, 0, i);
                    target += nums[i];
                    vis[i] = false;
                }
            }
        }
    }
}
