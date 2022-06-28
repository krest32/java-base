package com.krest.sqlserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.krest.Service.Response.R;
import com.krest.sqlserver.service.UserService;
import org.springframework.stereotype.Service;
import com.krest.sqlserver.entity.WinterUser;
import com.krest.sqlserver.mapper.UserMapper;

import java.util.List;

/**
 * @author: krest
 * @date: 2021/6/22 16:07
 * @description:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, WinterUser> implements UserService {


    @Override
    public R queryList(Long page, Long limit, WinterUser user) {
        QueryWrapper<WinterUser> queryWrapper = new QueryWrapper<>();
        Page<WinterUser> winterUserPage = new Page<>(page,limit);

        IPage<WinterUser> winterUserIPage = baseMapper.selectPage(winterUserPage, queryWrapper);
        List<WinterUser> records = winterUserIPage.getRecords();
        long total = winterUserIPage.getTotal();

        return R.ok().data("total",total).data("list",records);
    }
}
