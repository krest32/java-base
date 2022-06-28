package com.krest.sqlserver.service;

import com.krest.Service.Response.R;
import com.krest.sqlserver.entity.User;

import java.util.List;

/**
 * @author: krest
 * @date: 2021/6/22 16:07
 * @description:
 */
public interface UserService {

    R pageQuery(Long page, Long limit, User user);

    List<User> list();

    User getById(String id);

    int update(User user);

    int insert(User user);

    int delete(String id);

    int count(User user);

    R queryList(User user);
}
