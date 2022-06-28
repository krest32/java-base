package Arithmetic.test;

public class MergeSort {
    public static void main(String args[]){
        int[] test = {9,2,6,3,5,7,7,7,10,11,12};
        merSort(test,0,test.length-1);
        for(int i=0; i<test.length;i++){
            System.out.print(test[i] + " ");
        }
    }

    // 无尽的将原来的数组进行分割
    private static void merSort(int[] arr, int left, int right) {
        if(left < right){
            int mid = (left + right) >> 1;
            merSort(arr, left, mid);
            merSort(arr, mid+1,  right);
            merge(arr,left,mid,right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int i = left;
        int[] arrSorted = new int[right-left+1];
        // 中位数
        int rp = mid+1;
        int st = 0;
        while (left<=mid && rp<=right){
            if(arr[left]<arr[rp]){
                arrSorted[st++] = arr[left];
            }else{
                arrSorted[st++] = arr[rp++];
            }
        }
        while(left<=mid){
            arrSorted[st++] = arr[left++];
        }
        while(rp<=right){
            arrSorted[st++] = arr[rp++];
        }

        for(int st2 = 0; st2<arrSorted.length; st2++){
            arr[st2+i] = arrSorted[st2];
        }
    }
}
