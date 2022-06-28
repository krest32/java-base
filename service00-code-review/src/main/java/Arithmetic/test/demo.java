package Arithmetic.test;

/**
 * Description：
 * Author: Krest
 * Date: 2021/10/19 22:29
 */
public class demo {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,4,4};
        int len = nums.length;
        for(int num: nums){
            int x = (num-1)%len;
            nums[x] +=len;
        }


        for(int i =0; i<len; i++){
            System.out.println(nums[i]);
            if (nums[i]<=len){
                System.out.println(i+1);
            }
        }
    }
}
