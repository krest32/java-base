package JavaSE.ConCurrent.ForkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class DemoTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test1();  //8095 ms 循环累加
        test2();  //14480 ms    fokin 按照正常来书，应该更快
        test3();  //762 ms    Stream 流会让计算机运行更快，ForkJoin 看情况
    }


    public static void test1(){
        long sum = 0L;
        long start = System.currentTimeMillis();
        for (Long i = 1L; i <10_0000_0000 ; i++) {
            sum =+ i ;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum="+sum+"时间："+(end-start));
    }

    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> demo = new Demo(0L, 10_0000_0000L);
        //提交任务
        ForkJoinTask<Long> submit = forkJoinPool.submit(demo);
        Long sum = submit.get();

        long end = System.currentTimeMillis();

        System.out.println("sum="+sum+"  时间："+(end-start));
    }


    public static void test3(){
        Long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0, Long::sum);
        Long end = System.currentTimeMillis();

        System.out.println("sum="+sum+"  时间："+(end-start));
    }
}
