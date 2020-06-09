package com.imooc.Filtel;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;
//@Component
public class TimeFiltel implements Filter {
    /**
     * 初始化方法
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化");
    }

    /**
     * 执行的东西
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
           long stater = new Date().getTime();
            chain.doFilter(request,response);
            long end= new Date().getTime();
        System.out.println(end);
    }

    /**
     * 销毁时方法
     */
    @Override
    public void destroy() {

    }
}
