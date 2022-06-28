package Arithmetic.code;

/**
 * @author: krest
 * @date: 2021/4/22 20:16
 * @description: 直接选择排序
 * 在要排序的一组数中，选出最小的一个数与第一个位置的数交换；
 * 然后在剩下的数当中再找最小的与第二个位置的数交换，
 * 如此循环到倒数第二个数和最后一个数比较为止。
 */
public class StraightSelectionSort {
    public static void main(String[] args) {
        int[] arr= {5,2,0,0,1,0,6,1,3};
        int[] arrSorted = sort(arr);
        for (int i = 0; i < arrSorted.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }

    private static int[] sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j]<arr[i]){
                    int temp=arr[j];
                    arr[j]=arr[i];
                    arr[i]=temp;
                }
            }
        }
        return arr;
    }
}
