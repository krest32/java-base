package com.krest.mybatis.service;

import com.krest.Service.Response.R;
import com.krest.mybatis.entity.Apps;

import java.util.List;

/**
 * @author: krest
 * @date: 2021/5/18 21:07
 * @description:
 */
public interface AppsService {

    Apps getOne(String id);

    List<Apps> getAll();

    int delete(String id);

    int update(Apps apps);

    int count(Apps apps);

    R pageQuery(Long page, Long limit, Apps apps);

    R queryList(Apps apps);

    R selectBySql(String sql);
}
