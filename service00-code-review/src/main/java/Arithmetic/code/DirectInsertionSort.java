package Arithmetic.code;

/**
 * @author: krest
 * @date: 2021/4/22 18:19
 * @description: 直接插入排序
 * 基本思想：在要排序的一组数中，假设前面的数已经是排好顺序的，现在要把第n 个数插到前面的有序数中，
 * 使得这 n个数也是排好顺序的。如此反复循环，直到全部排好顺序。 插入的位置和前一个元素对比，较小就交换
 */
public class DirectInsertionSort {
    public static void main(String[] args) {
        int[] arr = {5,2,0,0,1,0,6,1,3};
        int[] arrSorted = sort(arr);
        for (int value : arrSorted) {
            System.out.print(value + " ");
        }
    }

    private static int[] sort(int[] arr) {
        int insetIndex =0 ;
        for (int i = 1; i < arr.length; i++) {
            // 将需要比较的变量设置为临时变量
            int temp = arr[i];
            insetIndex=i;
            while(insetIndex>0){
                if (arr[insetIndex-1]>temp){
                    arr[insetIndex]=arr[insetIndex-1];
                    arr[insetIndex-1]=temp;
                }
                insetIndex--;
            }
        }
        return arr;
    }
}
