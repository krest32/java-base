package Arithmetic.code;

/**
 * @author: krest
 * @date: 2021/4/22 20:29
 * @description: 冒泡排序 它重复地走访过要排序的元素列，依次比较两个相邻的元素，如果他们的顺序（如从大到小、首字母从A到Z）错误就把他们交换过来。
 *     走访元素的工作是重复地进行直到没有相邻元素需要交换，也就是说该元素列已经排序完成
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        int[] arrSorted = sort(arr);
        for (int i = 0; i < arrSorted.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static int[] sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }
}
