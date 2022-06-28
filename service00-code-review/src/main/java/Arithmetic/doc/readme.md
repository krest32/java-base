# 算法
### 直接插入法（DirectInsertionSort）

![在这里插入图片描述](https://duxin2010.oss-cn-beijing.aliyuncs.com/20210422205135.gif)

~~~java
package Arithmetic;

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
        for (int i = 0; i < arrSorted.length; i++) {
            System.out.print(arrSorted[i]+" ");
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

~~~

输出结果

~~~
0 0 0 1 1 2 3 5 6 
~~~

###  直接选择法

![在这里插入图片描述](https://duxin2010.oss-cn-beijing.aliyuncs.com/20210422205107.gif)

~~~java
package Arithmetic;

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
        int[] arr= { 3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48 };
        int[] arrSorted = sort(arr);
        for (int i = 0; i < arrSorted.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }

    private static int[] sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int temp;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j]<arr[i]){
                    temp=arr[j];
                    arr[j]=arr[i];
                    arr[i]=temp;
                }
            }
        }
        return arr;
    }
}
~~~

### 冒泡排序法

![在这里插入图片描述](https://duxin2010.oss-cn-beijing.aliyuncs.com/20210422205048.gif)

~~~java
package Arithmetic;

/**
 * @author: krest
 * @date: 2021/4/22 20:29
 * @description: 冒泡排序
 * 它重复地走访过要排序的元素列，依次比较两个相邻的元素，如果他们的顺序（如从大到小、首字母从A到Z）错误就把他们交换过来。
 * 走访元素的工作是重复地进行直到没有相邻元素需要交换，也就是说该元素列已经排序完成
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr= { 3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48 };
        int[] arrSorted = sort(arr);
        for (int i = 0; i < arrSorted.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }

    private static int[] sort(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j <arr.length-1-i ; j++) {
                if(arr[j]>arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1]=arr[j];
                    arr[j]=temp;
                }
            }
        }
        return arr;
    }
}

~~~



### 快速排序法

![在这里插入图片描述](https://duxin2010.oss-cn-beijing.aliyuncs.com/20210422205210.gif)

~~~Java
package Arithmetic;

/**
 * @author: krest
 * @date: 2021/4/22 20:53
 * @description: 快速排序
 *  快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。具体算法描述如下：
 *
 * 1. 从数列中挑出一个元素，称为 “基准”（pivot）；
 * 2. 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
 *      在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 * 3. 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {6, 9, 1, 4, 5, 8, 7, 0, 2, 3};
        quickSort(arr, 0, arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left==right){
            return;
        }
        int partitionNum = partitionNum(arr,left,right);
        quickSort(arr,left,partitionNum);
        quickSort(arr,partitionNum+1,right);
    }

    private static int partitionNum(int[] arr, int left, int right) {
        int pivot = arr[left];
        while (left<right){
            while (left<right && arr[right]>pivot){
                right--;
            }
            arr[left]=arr[right];
            while (left<right && arr[left]<pivot){
                left++;
            }
            arr[right]=arr[left];
        }
        arr[left]=pivot;
        return left;
    }
}
~~~

### 归并排序

![在这里插入图片描述](https://duxin2010.oss-cn-beijing.aliyuncs.com/20210422221646.gif)

~~~java
package Arithmetic;
/**
 * @author: krest
 * @date: 2021/4/22 22:20
 * @description: 归并排序
 * 归并排序利用的是分治的思想实现的，
 * 对于给定的一组数据，利用递归与分治技术将数据序列划分成为越来越小的子序列，
 * 之后对子序列排序，最后再用递归方法将排好序的子序列合并成为有序序列。合并两个子序列时，
 * 需要申请两个子序列加起来长度的内存，临时存储新的生成序列，再将新生成的序列赋值到原数组相应的位置。
 */
public class MergeSort {
    public static void main(String args[]){
        int[] test = {9,2,6,3,5,7,10,11,12};
        merSort(test,0,test.length-1);
        for(int i=0; i<test.length;i++){
            System.out.print(test[i] + " ");
        }
    }
    
    
    public static void merSort(int[] arr,int left,int right){
        if(left<right){
            int mid =(left+right)>>1;
            merSort(arr,left,mid);
            merSort(arr,mid+1,right);
            merge(arr,left,mid,right);
        }

    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int i =left;
        int[] arrSorted=new int[right - left + 1];;
        int rp = mid+1;
        int st=0;

        while (left<=mid && rp<=right ){
            if (arr[left] < arr[rp]) {
                arrSorted[st++] = arr[left++];
            } else {
                arrSorted[st++] = arr[rp++];
            }
        }

        while (left<=mid){
            arrSorted[st++]=arr[left++];
        }

        while (rp<=right){
            arrSorted[st++]=arr[rp++];
        }
        
        //将temp中的元素全部拷贝到原数组中
        for (int st2 = 0; st2 < arrSorted.length; st2++) {
            arr[st2 + i] = arrSorted[st2];
        }
    }
}

~~~

# 常见算法面试题

### 二分法

~~~java

~~~


