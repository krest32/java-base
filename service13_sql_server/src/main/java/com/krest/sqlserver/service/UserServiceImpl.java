package com.krest.sqlserver.service;

import com.krest.Service.Response.R;
import com.krest.sqlserver.entity.User;
import com.krest.sqlserver.mapper.UserMapper;
import com.krest.sqlserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: krest
 * @date: 2021/6/22 16:07
 * @description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public R pageQuery(Long page, Long limit, User user) {
        page = (page-1)*limit;
        Map<String,Object> map = new HashMap<>();
        map.put("page",page);
        map.put("limit",limit);
        map.put("user",user);
        System.out.println(page+" "+limit);
        List<User> listPager = userMapper.getListPager(map);
        int count = userMapper.getCount(user);
        return R.ok().data("total",count).data("list",listPager);
    }

    @Override
    public List<User> list() {
        List<User> list = userMapper.list();
        return list;
    }

    @Override
    public User getById(String id) {
        User one = userMapper.getOne(id);
        return one;
    }

    @Override
    public int update(User user) {
        System.out.println(user.getId()+user.getName());
        int i = userMapper.updateUser(user);
        return i;
    }

    @Override
    public int insert(User user) {
        int insert = userMapper.insert(user);
        return insert;
    }

    @Override
    public int delete(String id) {
        int i = userMapper.delete(id);
        return i;
    }

    @Override
    public int count(User user) {
        int count = userMapper.getCount(user);
        return count;
    }

    @Override
    public R queryList(User user) {
        Map<String,Object> map = new HashMap<>();
        map.put("user",user);
        List<User> listPager = userMapper.queryList(map);
        int count = userMapper.getCount(user);
        return R.ok().data("total",count).data("list",listPager);
    }


}
