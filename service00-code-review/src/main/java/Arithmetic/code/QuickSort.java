package Arithmetic.code;

/**
 * @author: krest
 * @date: 2021/4/22 20:53
 * @description: 快速排序 快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。具体算法描述如下： 1. 从数列中挑出一个元素，称为 “基准”（pivot）；
 *     2. 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
 *     在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作； 3. 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {6, 9, 1, 4, 5, 8, 7, 0, 2, 4, 4, 3, 123};

        quickSort(arr, 0, arr.length - 1);

        for (int value : arr) {
            System.out.print(value + " ");
        }
    }

    /** 快速排序，利用利用递归 */
    private static void quickSort(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int partitionNum = partitionNum(arr, left, right);
        quickSort(arr, left, partitionNum);
        quickSort(arr, partitionNum + 1, right);
    }

    private static int partitionNum(int[] arr, int left, int right) {
        int pivot = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] < pivot) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = pivot;

        return left;
    }
}
