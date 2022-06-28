package Leetcode;

import java.util.Arrays;

public class rearrangeArray {
    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,2},{2,3},{4,5}};
        Arrays.sort(arr,(o1,o3) -> {
            return o3[1]-o1[1];
        });
    }
}
