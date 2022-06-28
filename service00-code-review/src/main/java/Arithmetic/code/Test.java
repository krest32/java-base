package Arithmetic.code;

import java.util.Arrays;

public class Test{
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,4,2,4,6,1,2,22,45,6,22,44545,7,68,442,2313};
        Merge.sort(arr);
        for (int value : arr) {
            System.out.print(value + " ");
        }
    }
}