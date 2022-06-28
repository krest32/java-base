package JavaSE.Other;

/**
 * @author: krest
 * @date: 2021/6/2 23:57
 * @description: 遍历数组
 */
public class Iterator {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3};
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
