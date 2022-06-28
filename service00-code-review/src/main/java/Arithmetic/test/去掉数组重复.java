package Arithmetic.test;

/**
 * @author: krest
 * @date: 2021/4/26 20:12
 * @description: 不需要考虑数组中超出新长度后面的元素。
 */
public class 去掉数组重复 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,4,5,6};
        Solution solution = new Solution();
        System.out.println(solution.removeDuplicates(nums));
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
        }

    }
}

class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        int i=0;
        for(int j=0; j<nums.length; j++){
            if(nums[j]!=nums[i]){
                i++;
                nums[i]=nums[j];
            }
        }
        return i+1;
    }
}
