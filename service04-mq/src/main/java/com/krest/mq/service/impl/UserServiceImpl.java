package com.krest.mq.service.impl;

import com.krest.Service.Response.R;
import com.krest.mq.entity.User;
import com.krest.mq.mapper.UserMapper;
import com.krest.mq.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author krest
 * @since 2021-05-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public R clearMsg() {
       return R.ok();
    }


}
