package com.krest.sqlserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.krest.Service.Response.R;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.krest.sqlserver.entity.WinterUser;
import com.krest.sqlserver.service.UserService;

import java.util.List;

/**
 * @author: krest
 * @date: 2021/6/22 16:29
 * @description:
 */
@Api(tags = "测试SqlServer")
@RestController
@RequestMapping("/mybatisplus/winteruser")
public class SqlServerController {

    @Autowired
    private UserService userService;

    @ApiOperation("测试基本功能")
    @GetMapping("hello")
    public R hello(){
        return R.ok().data("hello","你好");
    }

    @ApiOperation("列出所有")
    @GetMapping("getAll")
    public R getAll(){
        List<WinterUser> list = userService.list(null);
        return R.ok().data("list",list);
    }

    @ApiOperation("根据Id查找")
    @GetMapping("getById/{id}")
    public R getById(@PathVariable String id){
        WinterUser user = userService.getById(id);
        return R.ok().data("user",user);
    }


    /**
     * 分页功能无法实现
     */
    @ApiOperation("条件分页查询")
    @PostMapping("queryList")
    public R queryList(@RequestParam Long page,
                     @RequestParam Long limit,
                     @RequestBody(required = false) WinterUser user ){
        return userService.queryList(page,limit,user);
    }




}
