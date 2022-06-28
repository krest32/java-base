package com.krest.shiro.utils;

import com.krest.shiro.entity.Account;
import com.krest.shiro.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;


public class AccountRealm extends AuthorizingRealm {

    @Autowired
    private AccountService accountService;

    /**
     * 授权
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取当前登录的用户信息
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();
        //设置角色
        Set<String> rolesset = new HashSet<>();
        rolesset.add(account.getRole());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(rolesset);
        //设置权限
        info.addStringPermission(account.getPerms());
        return info;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        Account account = accountService.findByUsername(token.getUsername());
        if (account != null) {
            //若密码不正确则返回IncorrectCredentialsException异常
            return new SimpleAuthenticationInfo(account, account.getPassword(), getName());
        }
        //若用户名不存在则返回UnknownAccountException异常
        return null;
    }

}