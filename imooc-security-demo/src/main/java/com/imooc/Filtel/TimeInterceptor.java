package com.imooc.Filtel;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


/**
 * 拦截器
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {
    /**
     * 在某一个方法调用之前这个方法会被调用
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("perHandle");
        //打印全路径类名
        System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
        //打印全路径方法名
        System.out.println(((HandlerMethod)handler).getMethod().getName());
        request.setAttribute("startTime",new Date().getTime());


        return true;
    }

    /**
     * 控制器的方法处理之后这个方法会被调用
     * 如果有异常了 这个方法就不会被调用了
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        long startTime = (long) request.getAttribute("startTime");
        System.out.println("********"+ (new Date().getTime()-startTime));
    }

    /**
     * 不管是正常还是异常这个方法还是会被调用
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
        long startTime = (long) request.getAttribute("startTime");
        System.out.println("********"+ (new Date().getTime()-startTime));

        System.out.println("ex +++"+ ex);
    }
}
