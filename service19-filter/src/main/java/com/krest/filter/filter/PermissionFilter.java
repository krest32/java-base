package com.krest.filter.filter;

import com.krest.filter.common.MyConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author Administrator
 */
@Component
/* 不推荐使用这个注解，虽然有时候会比较方便，但是可能会存在一些其他问题 @WebFilter */
@WebFilter(filterName = "CharsetFilter",
        urlPatterns = "/*",     /*通配符（*）表示对所有的web资源进行拦截 */
        initParams = {
                @WebInitParam(name = "charset", value = "utf-8")
                /* 这里可以放一些初始化的参数 */
        })
public class PermissionFilter implements Filter {

    private MyConfig config;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 解决可能存在 filter 无法依赖注入的问题 ，可以代替 Autowired
        ServletContext sc = filterConfig.getServletContext();
        WebApplicationContext cxt =  WebApplicationContextUtils.getWebApplicationContext(sc);
        if (cxt != null && cxt.getBean("myConfig") != null){
            config = (MyConfig) cxt.getBean("myConfig");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        // 校验Auth
        if(req.getHeader("authName")!=null
                && req.getHeader("authName").equals(config.getAuthName())
                && req.getHeader("authPassword")!=null
                && req.getHeader("authPassword").equals(config.getAuthPassword())
        ){
            chain.doFilter(req, resp);
        }else{
            resp.getWriter().write("wrong");
        }
    }
    @Override
    public void destroy() {

    }
}
