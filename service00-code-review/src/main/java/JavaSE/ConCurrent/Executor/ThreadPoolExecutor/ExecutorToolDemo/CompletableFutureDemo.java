package JavaSE.ConCurrent.Executor.ThreadPoolExecutor.ExecutorToolDemo;



import JavaSE.ConCurrent.Executor.ThreadPoolExecutor.config.MyThreadConfig;
import JavaSE.ConCurrent.Executor.ThreadPoolExecutor.config.ThreadPoolConfigProperties;
import JavaSE.ConCurrent.Executor.entity.User;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

public class CompletableFutureDemo {

    public static void main(String[] args) throws Exception {
        // 建立一个新的线程池
        ThreadPoolExecutor executor = MyThreadConfig.threadPoolExecutor(
                new ThreadPoolConfigProperties(2,3,30000));

        User user = new User();

        // 开启并执行一个异步任务，可以指定我们的线程池
        CompletableFuture<User> setUserIdFuture = CompletableFuture.supplyAsync(() -> {
            user.setId(Thread.currentThread().getName());
            return user;
        }, executor);

        // 接收上一个任务的结果,并且通过异步的方式来执行
        CompletableFuture<Void> setUserNameFuture = setUserIdFuture.thenAcceptAsync((res) -> {
            user.setName(Thread.currentThread().getName());
        }, executor);

        CompletableFuture<Void> setUserAddressFuture = setUserNameFuture.thenAcceptAsync((res) -> {
            user.setAddress(Thread.currentThread().getName());
        }, executor);

        CompletableFuture<Void> setUserAgeFuture = setUserAddressFuture.thenAcceptAsync((res) -> {
            user.setAge(Thread.currentThread().getName());
        }, executor);

        // 所有的任务全部完成以后
        CompletableFuture.allOf(setUserIdFuture, setUserNameFuture, setUserAddressFuture,setUserAgeFuture).get();
        System.out.println(user);

    }
}
