package com.imooc.config;


import com.imooc.Filtel.TimeFiltel;
import com.imooc.Filtel.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

//@Configuration
public class WebComfig extends WebMvcConfigurerAdapter {

   // @Autowired
    private TimeInterceptor timeInterceptor;

    /**
     * 定义拦截器必须定义 WebMvcConfigurerAdapter
     *  从写addInterceptors  加载到里面
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }

    /**
 * 怎么加载第三方过滤器
 */

//@Bean
    public FilterRegistrationBean timeFiltel(){
    FilterRegistrationBean filterRegistrationBean =new FilterRegistrationBean();
    //创建第三方过滤器对象
    TimeFiltel timeFiltel = new TimeFiltel();
    filterRegistrationBean.setFilter(timeFiltel);
    //创建集合用来装过滤的路径
    List<String> urls = new ArrayList<>();
    //    /*  代表都可以
    urls.add("/*");
    filterRegistrationBean.setUrlPatterns(urls);
    return filterRegistrationBean;

}


}
