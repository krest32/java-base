package JavaSE.ConCurrent.ForkJoin;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Description：使用Fork/Join运行归并算法
 *
 *              但是随着待排序集合中数据规模继续增大，
 *              以上归并算法的代码实现就有一些力不从心了，
 *              例如以上算法对1亿条随机数集合进行排序时，耗时为27秒左右。
 *
 *              接着我们可以使用Fork/Join框架来优化归并算法的执行性能，
 *              将拆分后的子任务实例化成多个ForkJoinTask任务放入待执行队列，
 *              并由Fork/Join框架在多个ForkJoinWorkerThread线程间调度
 *              这些任务。如下图所示：
 * Author: Krest
 * Date: 2021/9/5 15:27
 */
public class Demo3 {
    private static int MAX = 100000000;

    private static int inits[] = new int[MAX];

    // 同样进行随机队列初始化，这里就不再赘述了
    static {
        //......
    }

    public static void main(String[] args) throws Exception {
        // 正式开始
        long beginTime = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        MyTask task = new MyTask(inits);
        ForkJoinTask<int[]> taskResult = pool.submit(task);
        try {
            taskResult.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace(System.out);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("耗时=" + (endTime - beginTime));
    }

    /**
     * 单个排序的子任务
     * @author yinwenjie
     */
    static class MyTask extends RecursiveTask<int[]> {

        private int source[];

        public MyTask(int source[]) {
            this.source = source;
        }

        /* (non-Javadoc)
         * @see java.util.concurrent.RecursiveTask#compute()
         */
        @Override
        protected int[] compute() {
            int sourceLen = source.length;
            // 如果条件成立，说明任务中要进行排序的集合还不够小
            if(sourceLen > 2) {
                int midIndex = sourceLen / 2;
                // 拆分成两个子任务
                MyTask task1 = new MyTask(Arrays.copyOf(source, midIndex));
                task1.fork();
                MyTask task2 = new MyTask(Arrays.copyOfRange(source, midIndex , sourceLen));
                task2.fork();
                // 将两个有序的数组，合并成一个有序的数组
                int result1[] = task1.join();
                int result2[] = task2.join();
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

        private int[] joinInts(int array1[] , int array2[]) {
            // 和上文中出现的代码一致
            return null;
        }
    }

}
