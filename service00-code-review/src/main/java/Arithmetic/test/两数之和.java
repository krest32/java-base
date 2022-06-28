package Arithmetic.test;

/**
 * @author: krest
 * @date: 2021/4/26 21:42
 * @description:
 */
public class 两数之和 {
    public static void main(String[] args) {
        int[] nums = {-1,2,-3};
        int target =-4;
        int[] i = Solution.twoSum(nums,target);
        for (int i1 : i) {
            System.out.println(i1);
        }
    }
    static class Solution{
        public static int[] twoSum(int[] nums,int taget) {
            for (int i = 0; i < nums.length; i++) {
                for (int j=i+1;j<nums.length;j++){
                    if(nums[i]+nums[j] == taget){
                        return new int[]{i,j};
                    }
                }
            }
            return new int[0];
        }

    }

}

