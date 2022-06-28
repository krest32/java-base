package com.krest.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.krest.demo.entity.User;
import com.krest.demo.service.EmployeeService;
import com.krest.demo.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping
@CrossOrigin
@RestController
public class MongoDBDemoController {
    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("mongodb")
    public String testMongoDB() {
        List<User> userList = mongoTemplate.findAll(User.class);
        return JSONObject.toJSONString(userList);
    }

    @Resource
    private EmployeeService employeeService;

    /**
     * 新增接口
     */
    @GetMapping("/create")
    public Response create() {
        return Response.success(employeeService.create());
    }

    /**
     * 更新接口
     */
    @GetMapping("/update")
    public Response update() {
        return Response.success(employeeService.update());
    }

    /**
     * 删除接口
     */
    @GetMapping("/delete")
    public Response delete() {
        return Response.success(employeeService.delete());
    }

    /**
     * 查询接口
     */
    @GetMapping("/select")
    public Response select() {
        return Response.success(employeeService.select());
    }
}
