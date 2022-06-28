package DataStructure.code.search;

/**
 * @author: krest
 * @date: 2021/1/3 17:21
 * @description: binary search 2
 */
public class BinarySearch2 {
    public static void main(String[] args) {
        int[] arr= {1,2,3,4,5,6,7,8,9,10};
        int score = 11;
        // find low and  high value
        int low=0;
        int high = arr.length-1;
        int i = binarySearch3(arr, score, low, high);
        if (i==-1){
            System.out.println("error");
        }
    }

    /**
     * 递归二分查找法
     * @param arr 目标数组
     * @param key 目标数字
     * @param low 数组0
     * @param high 数组上标
     * @return  目标数字的index
     */
    public static int binarySearch3(int[] arr,int key ,int low, int high){
        if (low>high){
            return -1;
        }
        int mid =(low +high) /2 ;
        if (key == arr[mid]){
            System.out.println(mid);
            return mid;
        }else if( key < arr[mid]){
            return binarySearch3(arr,key,low,mid-1);
        }else{
            return binarySearch3(arr,key,mid+1,high);
        }
    }
}
