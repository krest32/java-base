package Arithmetic;

import java.util.*;

/**
 * @author: krest
 * @date: 2021/4/30 00:12
 * @description:
 */
public class Solution {
    public static void main(String[] args) {
        // 默认为空
        Boolean[][] flag = new Boolean[2][2];
        for (int i = 0; i < flag.length; i++) {
            Arrays.fill(flag[i], true);
        }
        
        
        System.out.println(flag[0][1]);
    }
}