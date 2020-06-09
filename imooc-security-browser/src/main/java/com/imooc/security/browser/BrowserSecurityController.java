package com.imooc.security.browser;

import com.imooc.security.core.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
public class BrowserSecurityController {


    //跳转之前把信息放在这个session里面
    private RequestCache requestCache = new HttpSessionRequestCache();
    //跳转的工具类
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Autowired
    private SecurityProperties securityProperties;

    /**
     *当身份认证是跳转到这里
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse  authentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //拿到引发跳转的这个请求
        SavedRequest request1 = requestCache.getRequest(request, response);
        if(request1 != null){
            //引发跳转的这个url
            String redirectUrl = request1.getRedirectUrl();
            //判断是否是html结尾
            if(StringUtils.endsWithIgnoreCase(redirectUrl,"html")){
                //跳转
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowserProperties().getLoginPage());
            }
        }


        return new SimpleResponse("访问的服务需要身份验证");
    }
}
