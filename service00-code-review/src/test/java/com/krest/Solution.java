package com.krest;

import java.util.HashMap;

class Solution {
    public static int subarraySum(int[] nums, int k) {
        int pre_sum = 0;
        int ret = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        // 输入一个默认值
        map.put(0, 1);
        for (int i : nums) {
            // 记录前i个数字的和
            pre_sum += i;
            // 记录何为0出现的次数,
            ret += map.getOrDefault(pre_sum - k, 0);
            map.put(pre_sum, map.getOrDefault(pre_sum, 0) + 1);
        }

        return ret;
    }

    public static void main(String[] args) {
        int[] arr = {1,1,1,1,1,1,1,1,1};
        System.out.println(subarraySum(arr, 2));
    }
}