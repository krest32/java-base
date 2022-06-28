package Arithmetic.test;

/**
 * @author: krest
 * @date: 2021/4/26 12:14
 * @description: 100以内有25个素数
 */
public class 统计素数 {
    public static void main(String[] args) {
        int n=100;
        // 暴力算法
        System.out.println(demo1(n));
        System.out.println(demo2(n));
    }

    /**
     * 埃筛法
     * @param n
     * @return
     */
    private static int demo2(int n) {
        // false 代表素数
        int count=0;
        boolean[] isPrime = new boolean[n];
        for(int i=2;i<n;i++){
            if (!isPrime[i]){
                count++;
                // 再循环中对系数进行递增
                for (int j = 2*i; j < n; j+=i) {
                    isPrime[j] = true;
                }
            }
        }
        return count;

    }


    /**
     * 暴力算法
     * @param n
     */
    private static int demo1(int n) {
        int count = 0;
        for(int i=2; i<n; i++){
            count +=  isPrime(i)? 1:0;
        }
        return count;
    }
    private static boolean isPrime(int x) {
        for (int i=2 ; i < x ;i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
}
