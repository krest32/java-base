package com.krest.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.krest.Service.entity.User;
import com.krest.mybatisplus.entity.WorkStatus;
import com.krest.mybatisplus.mapper.UserMapper;
import com.krest.mybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author krest
 * @since 2021-05-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    ThreadPoolExecutor executor;

    @Override
    public User getUserById(String id) throws ExecutionException, InterruptedException {
        WorkStatus status = new WorkStatus();
        User user = new User();

        // 开启并执行一个异步任务，可以指定我们的线程池
        CompletableFuture<User> userName = CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("第一个异步任务"+Thread.currentThread().getName() + "执行了" + i + "次");
            }
            status.setStatus1("1");
            user.setUsername(baseMapper.selectById(id).getUsername());
            return user;
        }, executor);

        // 接收上一个任务的结果,并且通过异步的方式来执行
        CompletableFuture<Void> userPassWord = userName.thenAcceptAsync((res) -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("第二个异步任务" + Thread.currentThread().getName() + "执行了" + i + "次");
            }
            status.setStatus1("2");
            user.setPassword(baseMapper.selectById(id).getPassword());
        }, executor);

        CompletableFuture<Void> userId = userName.thenAcceptAsync((res) -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("第三个异步任务" + Thread.currentThread().getName() + "执行了" + i + "次");
            }
            status.setStatus1("3");
            user.setId(id);
        }, executor);

        // 所有的任务全部完成以后
        CompletableFuture.allOf(userName, userPassWord, userId).get();
        return user;

    }
}
