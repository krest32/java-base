package com.krest.sqlserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.krest.Service.Response.R;
import com.krest.sqlserver.entity.WinterUser;

/**
 * @author: krest
 * @date: 2021/6/22 16:07
 * @description:
 */
public interface UserService extends IService<WinterUser> {


    R queryList(Long page, Long limit, WinterUser user);
}
