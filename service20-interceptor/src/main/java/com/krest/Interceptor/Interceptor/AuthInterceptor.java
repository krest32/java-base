package com.krest.Interceptor.Interceptor;

import com.krest.Interceptor.common.MyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义拦截器
 * @author Administrator
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private MyConfig config;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws IOException {

        // PreFlight请求，忽略本拦截器，预检请求
        if (CorsUtils.isPreFlightRequest(request)) {
            return true;
        }
        // 是否通过
        String username = "authName";
        String password = "authPassword";

        // 校验Auth
        if(request.getHeader(username) != null
                && request.getHeader(username).equals(config.getAuthName())
                && request.getHeader(password)!=null
                && request.getHeader(password).equals(config.getAuthPassword())
        ){
            return true;
        }else{
            response.getWriter().write("wrong");
            return false;
        }
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }




}
