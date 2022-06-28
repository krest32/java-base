package com.krest.mybatis.service.impl;

import com.krest.Service.Response.R;
import com.krest.mybatis.entity.Apps;
import com.krest.mybatis.mapper.AppsMapper;
import com.krest.mybatis.service.AppsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: krest
 * @date: 2021/5/18 21:38
 * @description:
 */
@Service
@Slf4j
public class AppserviceImpl implements AppsService {

    @Autowired
    private AppsMapper appsMapper;

    @Override
    public Apps getOne(String id) {
        Apps one = appsMapper.getOne(id);
        return one;
    }

    @Override
    public List<Apps> getAll() {
        List<Apps> all = appsMapper.getAll();
        return all;
    }

    @Override
    public int delete(String id) {
        int i = appsMapper.deleteApp(id);
        return i;
    }

    @Override
    public int update(Apps apps) {
        int i = appsMapper.updateApps(apps);
        return i;
    }

    @Override
    public int count(Apps apps) {
        int count = appsMapper.getCount(apps);
        return count;
    }

    @Override
    public R pageQuery(Long page, Long limit, Apps app) {
        page=page*limit-1;
        Map<String,Object> map = new HashMap<>();
        map.put("page",page);
        map.put("limit",limit);
        map.put("app",app);
        List<Apps> listPager = appsMapper.getListPager(map);
        int count = appsMapper.getCount(app);
        return R.ok().data("total",count).data("list",listPager);
    }

    @Override
    public R queryList(Apps app) {
        Map<String,Object> map = new HashMap<>();
        map.put("app",app);
        List<Apps> listPager = appsMapper.queryList(map);
        int count = appsMapper.getCount(app);
        return R.ok().data("total",count).data("list",listPager);
    }

    @Override
    public R selectBySql(String sql) {
        List<Apps> list = appsMapper.selectBySql(sql);
        return R.ok().data("list",list);
    }
}
