package Arithmetic.test;

/**
 * @author: krest
 * @date: 2021/4/26 20:25
 * @description: 找到x的平方整数根
 */
public class x的平方根 {
    public static void main(String[] args) {
        int n=2147395599;
        int x = mySqrt(n);
        System.out.println(x);
    }

    private static int mySqrt(int n) {
        int index = -1 ,l = 0, r= n;
        int mid = l+(r-l)/2;
        while(l< r){

            if (mid*mid <= n){
                index = mid;
                l= mid+1;
            }else{
                r=mid-1;
            }
        }
        return index;
    }
}
