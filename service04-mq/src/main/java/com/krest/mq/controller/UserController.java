package com.krest.mq.controller;


import com.krest.Service.Response.R;
import com.krest.mq.entity.User;
import com.krest.mq.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author krest
 * @since 2021-05-18
 */
@Api(tags = "测试RabbitMQ")
@RestController
@RequestMapping("/mq/user")
public class UserController {


    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    private UserService userService;

    @ApiOperation("1. hello接口功能调试")
    @GetMapping("hello")
    public String hello(){
        return "hello";
    }


    @ApiOperation("2. 测试给RabbitMq发送消息")
    @PostMapping("testMQ")
    public R testMQ(@RequestBody User user){
        rabbitTemplate.convertAndSend("userMessageQueue-event-exchange","userMessageQueue.create.userInfo",user);
        return R.ok().data("user",user);
    }


    @ApiOperation("3. 处理延时消息队列中的内容")
    @PostMapping("clearMsg")
    public R clearMsg(){
        return userService.clearMsg();
    }

}

