package Arithmetic.code;

/**
 * @author: krest
 * @date: 2021/4/22 23:48
 * @description: 二分查找-——折半查找 在一个有序的数组中找到关键值
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 6, 7, 9, 10, 11, 12};
        int key = 11;
        binarySearch(arr, key);
    }

    private static void binarySearch(int[] arr, int i) {
        int low = 0;
        int high = arr.length;
        search(arr, i, low, high);
    }

    private static int search(int[] arr, int i, int low, int high) {
        if (low > high) {
            System.out.println("没有这个数字");
            return -1;
        }
        int mid = (low + high) >> 1;
        if (arr[mid] == i) {
            System.out.println("有这个数字");
            return 1;
        } else if (arr[mid] > i) {
            return search(arr, i, low, mid - 1);
        } else {
            return search(arr, i, mid + 1, high);
        }
    }
}
