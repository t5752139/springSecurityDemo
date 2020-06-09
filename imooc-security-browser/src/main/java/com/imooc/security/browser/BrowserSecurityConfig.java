package com.imooc.security.browser;

import com.imooc.security.core.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 密码加密
     * @param http
     * @throws Exception
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new  BCryptPasswordEncoder();
    }

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                 //开启表单登录
        http.formLogin()
                //弹框登录
               // http.httpBasic()
                //登录后回去找着个路径下的url
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                //定义用自己写的登录成功的配置
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                //对请求做一个授权
                .authorizeRequests()
                //当访问这个页面是时候是不需要身份验证
                .antMatchers("/authentication/require",
                        securityProperties.getBrowserProperties().getLoginPage()).permitAll()
                //任何请求
                .anyRequest()
                //都需要身份认证
                .authenticated()
        .and()
                //把csrf关必掉
        .csrf()
        .disable();
    }
}
