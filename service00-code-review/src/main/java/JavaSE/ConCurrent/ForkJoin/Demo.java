package JavaSE.ConCurrent.ForkJoin;

import java.util.concurrent.RecursiveTask;

/**
 * 分支合并提升性能 ForkJoin
 */

public class Demo extends RecursiveTask<Long> {
    private long start;
    private long end;
    private long temp = 10_0000_0000L;

    public Demo() {
    }

    public Demo(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        Long sum = 0L;
        if ((end - start) > temp) {
            //分支合并
            for (Long i = start; i <= end; i++) {
                sum += 1;
            }
        } else {
            long middle = (start + end) / 2;
            Demo demo = new Demo(start, middle);
            demo.fork();
            Demo demo1 = new Demo(middle, end);
            demo.fork();
            sum = demo.join() + demo1.join();
        }
        return sum;
    }
}
