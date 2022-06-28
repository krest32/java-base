package JavaSE.Other;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: krest
 * @date: 2021/6/2 21:45
 * @description:
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        String s = "abcda";
        int len=getLengthOfLongestSubstring(s);
        System.out.println(len);
    }

    private static int getLengthOfLongestSubstring(String s) {
        int index = 0, ans=0;
        Set<Character> set = new HashSet<>();
        for(int i=0; i<s.length();i++){
            if(i != 0){
                // 保证set中没有重复的字符串
                set.remove(s.charAt(i-1));
            }
            // 如果小于最长长度，那么就不停向set中添加元素
            while(index < s.length() && !set.contains(s.charAt(index))){
                set.add(s.charAt(index));
                index++;
            }
            ans = Math.max(ans,index-i);
        }
        return ans;
    }
}
