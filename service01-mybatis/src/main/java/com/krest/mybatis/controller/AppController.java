package com.krest.mybatis.controller;

import com.krest.Service.Response.R;
import com.krest.mybatis.entity.Apps;
import com.krest.mybatis.service.AppsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: krest
 * @date: 2021/5/18 21:03
 * @description:
 */
@Api(tags = "测试mybatis功能方面（添加有devtool功能）")
@RequestMapping(value = "/app/mybatis",produces = "application/json; charset=utf-8",method = RequestMethod.POST)
@RestController
public class AppController  {

    @Autowired
    private AppsService appsService;

    @ApiOperation("测试Devtool功能")
    @RequestMapping("hello")
    public R hello(){
        return R.ok().data("hello","hello Devtool hello");
    }

    @ApiOperation("根据Id查找")
    @RequestMapping("getOne")
    public R getOne(@RequestParam(name = "id") String id){
        Apps app = appsService.getOne(id);
        return R.ok().data("data",app);
    }

    @ApiOperation("列出所有")
    @RequestMapping("getAll")
    public R getAll(){
        List<Apps> list = appsService.getAll();
        return R.ok().data("list",list);
    }

    @ApiOperation("根据Id删除")
    @RequestMapping("delete")
    public R delete(@RequestParam(name = "id") String id){
        int i = appsService.delete(id);
        return R.ok().data("num",i);
    }

    @ApiOperation("根据Id更新")
    @RequestMapping("update")
    public R update(Apps apps){
        int i = appsService.update(apps);
        return R.ok().data("num",i);
    }

    @ApiOperation("根据条件进行统计数量")
    @RequestMapping("count")
    public R count(Apps apps){
        int i = appsService.count(apps);
        return R.ok().data("num",i);
    }


    @ApiOperation("条件分页查询")
    @RequestMapping("pageQuery")
    public R queryPage(@RequestParam(name = "page") Long page,
                       @RequestParam(name = "limit") Long limit,
                       Apps apps){
        return appsService.pageQuery(page,limit,apps);

    }

    @ApiOperation("条件不分页")
    @RequestMapping("queryList")
    public R queryList(Apps apps){
        return appsService.queryList(apps);
    }



    @ApiOperation("根据Sql查找")
    @RequestMapping("selectBySql")
    public R selectBySql(@RequestParam(name = "sql") String sql){
        return appsService.selectBySql(sql);
    }

}
