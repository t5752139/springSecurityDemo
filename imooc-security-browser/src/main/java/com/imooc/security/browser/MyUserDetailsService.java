package com.imooc.security.browser;

import org.apache.tomcat.util.http.parser.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sun.security.util.Password;

@Component
public class MyUserDetailsService  implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 可以注入 bean信息
     * @autowriod
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
        @Autowired
        private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            logger.info(username);
            //根据用户明查找用户信息
        System.out.println(username);
        String encode = passwordEncoder.encode("123456");


        /**
         * 第一个账号
         * 第二个密码
         * 第三个  Collection<? extends GrantedAuthority> authorities   集合
         */                                                 //这个工具类是吧字符串 去掉逗号转成集合的
        User user = new User(username,encode, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

        return user;
    }
}
