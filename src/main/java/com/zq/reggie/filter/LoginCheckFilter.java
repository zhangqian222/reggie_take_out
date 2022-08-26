package com.zq.reggie.filter;


import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.zq.reggie.commen.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 过滤器来检查所有请求是否已经登录
 */
@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")//urlPatterns设置拦截路径
public class LoginCheckFilter implements Filter {
    //定义路径匹配器
    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();
    //定义需不需要拦截请求url
    private String[] url = {"/employee/login", "/employee/logout", "/backend/**", "/front/**"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //向下转型
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        //1.获取请求url
        String requestURI = httpServletRequest.getRequestURI();
        //2.调用matchPath方法判断是否可以匹配上路径
        //2.1 可以匹配上，放行
        if (matchPath(requestURI, url)) {
            log.info("放行的请求url:{}", requestURI);
            chain.doFilter(request, response);
            return;
        }
        //2.2 没有匹配上url，判断用户是否登录
        HttpSession session = httpServletRequest.getSession();
        Object employee = session.getAttribute("employee");
        if (employee != null) {
            log.info("已经登录且放行的请求url:{}", requestURI);
            chain.doFilter(request, response);
            return;
        }
        //3. 返回登录界面
        log.info("拦截的请求url:{}", requestURI);
        //JSON.toJSONString 将对象转为json格式的方法
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }

    /**
     * 判断路径是否匹配
     *
     * @param requestURI
     * @param url
     * @return
     */
    public boolean matchPath(String requestURI, String[] url) {
        //变量url数组，查看是否匹配
        for (int i = 0; i < url.length; i++) {
            //匹配上，直接返回true
            if (antPathMatcher.match(url[i], requestURI)) {
                return true;
            }
        }
        return false;
    }
}
