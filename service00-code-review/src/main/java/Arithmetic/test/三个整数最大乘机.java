package Arithmetic.test;

/**
 * @author: krest
 * @date: 2021/4/26 21:42
 * @description:
 */
public class 三个整数最大乘机 {
    public static void main(String[] args) {
        int[] nums = {-1,2,-3};
        int i = Solution.maximumProduct(nums);
        System.out.println(i);
    }
    static class Solution{
        public static int maximumProduct(int[] nums) {
            // 最小的和第二小的(因为不知道那个最小，所以定义一个阀值)
            int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
            System.out.println(min1);
            // 最大的、第二大的和第三大的
            int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;

            for (int x : nums) {
                if (x < min1) {
                    min2 = min1;
                    min1 = x;
                } else if (x < min2) {
                    min2 = x;
                }

                if (x > max1) {
                    max3 = max2;
                    max2 = max1;
                    max1 = x;
                } else if (x > max2) {
                    max3 = max2;
                    max2 = x;
                } else if (x > max3) {
                    max3 = x;
                }
            }

            return Math.max(min1 * min2 * max1, max1 * max2 * max3);

        }

    }

}

