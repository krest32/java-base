package com.krest.shiro.controller;

import com.krest.shiro.entity.Account;
import com.krest.shiro.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountController {

    @Autowired
    private AccountService acccoutService;


    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }


    /**
     * 重定向
     *
     * @param url
     * @return
     */
    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url) {
        return url;
    }

    /**
     * 进入到登陆页面，有两个功能
     * 1. 登陆
     * 2. 注册
     *
     * @param username
     * @param password
     * @param model
     * @return
     */
    @PostMapping("/login")
    public String login(String username, String password, Model model) {
        // 用来记住当前登陆的主体
        Subject subject = SecurityUtils.getSubject();
        Account ac = acccoutService.findByUsername(username);
        // 如果找到了当前的用户,就进行验证
        if (ac != null) {
            //根据salt值和用户输入的密码计算加密后的密码
            String salt = ac.getSalt();
            password = new SimpleHash("md5", password, salt, 2).toString();
        }
        // 使用UserName 和 PassWord 生成一个 Token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            // 使用Token 进行登陆
            subject.login(token);
            Account account = (Account) subject.getPrincipal();
            // 设置属性权限，跳转到首页
            subject.getSession().setAttribute("account", account);
            return "index";
            // 如果登陆但是不存在
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            model.addAttribute("msg", "用户名不存在");
            return "login";
            // 如果无法通过认证
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            model.addAttribute("msg", "密码有误");
            return "login";
        }
    }

    @ResponseBody
    @GetMapping("/unauthor")
    public String unauthor() {
        return "权限不足，无法访问";
    }

    @GetMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    @PostMapping("/register")
    public String register(Account account, Model model) {
        String username = account.getUsername();
        String password = account.getPassword();
        if (username == null || username == "") {
            model.addAttribute("msg", "用户名不能为空");
            return "register";
        } else if (password == null || password == "") {
            model.addAttribute("msg", "密码不能为空");
            return "register";
        } else if (acccoutService.findByUsername(username) != null) {
            model.addAttribute("msg", "用户名已被占用");
            return "register";
        } else {
            acccoutService.createAccount(account);
            return "login";
        }
    }

}