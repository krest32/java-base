package JavaSE.ConCurrent.ForkJoin;

import org.apache.commons.collections4.collection.SynchronizedCollection;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

/**
 * Description：:::::::::使用归并算法解决排序问题:::::
 *              以上归并算法对1万条随机数进行排序只需要2-3毫秒，
 *              对10万条随机数进行排序只需要20毫秒左右的时间，
 *              对100万条随机数进行排序的平均时间大约为160毫秒
 *              （这还要看随机生成的待排序数组是否本身的凌乱程度）。
 *              可见归并算法本身是具有良好的性能的。使用JMX工具和
 *              操作系统自带的CPU监控器监视应用程序的执行情况，可
 *              以发现整个算法是单线程运行的，且同一时间CPU只有单
 *              个内核在作为主要的处理内核工作
 * Author: Krest
 * Date: 2021/9/5 15:24
 */
public class Demo2 {

        private static int MAX = 10000;

        private static int inits[] = new int[MAX];

        // 这是为了生成一个数量为MAX的随机整数集合，准备计算数据
        // 和算法本身并没有什么关系
        // 同样进行随机队列初始化，这里就不再赘述了
        static {
            Random r = new Random();
            for(int index = 1 ; index <= MAX ; index++) {
                inits[index - 1] = r.nextInt(10000000);
            }
        }

        public static void main(String[] args) {
            long beginTime = System.currentTimeMillis();
            int results[] = forkits(inits);
            long endTime = System.currentTimeMillis();
            // 如果参与排序的数据非常庞大，记得把这种打印方式去掉
            System.out.println("耗时=" + (endTime - beginTime) + " | " + Arrays.toString(results));
        }

        // 拆分成较小的元素或者进行足够小的元素集合的排序
        private static int[] forkits(int source[]) {
        int sourceLen = source.length;
        if(sourceLen > 2) {
            int midIndex = sourceLen / 2;
            int result1[] = forkits(Arrays.copyOf(source, midIndex));
            int result2[] = forkits(Arrays.copyOfRange(source, midIndex , sourceLen));
            // 将两个有序的数组，合并成一个有序的数组
            int mer[] = joinInts(result1 , result2);
            return mer;
        }
        // 否则说明集合中只有一个或者两个元素，可以进行这两个元素的比较排序了
        else {
            // 如果条件成立，说明数组中只有一个元素，或者是数组中的元素都已经排列好位置了
            if(sourceLen == 1
                    || source[0] <= source[1]) {
                return source;
            } else {
                int targetp[] = new int[sourceLen];
                targetp[0] = source[1];
                targetp[1] = source[0];
                return targetp;
            }
        }
    }

        /**
         * 这个方法用于合并两个有序集合
         * @param array1
         * @param array2
         */
        private static int[] joinInts(int array1[] , int array2[]) {
        int destInts[] = new int[array1.length + array2.length];
        int array1Len = array1.length;
        int array2Len = array2.length;
        int destLen = destInts.length;

        // 只需要以新的集合destInts的长度为标准，遍历一次即可
        for(int index = 0 , array1Index = 0 , array2Index = 0 ; index < destLen ; index++) {
            int value1 = array1Index >= array1Len?Integer.MAX_VALUE:array1[array1Index];
            int value2 = array2Index >= array2Len?Integer.MAX_VALUE:array2[array2Index];
            // 如果条件成立，说明应该取数组array1中的值
            if(value1 < value2) {
                array1Index++;
                destInts[index] = value1;
            }
            // 否则取数组array2中的值
            else {
                array2Index++;
                destInts[index] = value2;
            }
        }

        return destInts;
    }
}
