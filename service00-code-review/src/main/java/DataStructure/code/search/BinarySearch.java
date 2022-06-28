package DataStructure.code.search;

/**
 * @author: krest
 * @date: 2021/1/3 17:21
 * @description: binary search
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr= {1,2,3,4,5,6,7,8,9,10};

        int score = 3;

        int flag = -1;

        // find low and  high value
        int low=0;
        int high = arr.length-1;

        while (low < high){
            int mid=(low+high)/2;
            if(score == arr[mid]){
                System.out.println("arr.index:"+mid);
                flag = 1;
                break;
            }else if(score < arr[mid]){
                high = mid-1;
            }else {
                low = mid+1 ;
            }
        }
        if (flag==-1){
            System.out.println("arr.index not exist");
        }
    }
}
