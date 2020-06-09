package com.imooc.security.validata;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**定义一个过滤器
 * 继承spring提供的工具类 保证每次调用一次
 *
 */
public class ValidataCodeFeiltel extends OncePerRequestFilter {

    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public SessionStrategy getSessionStrategy() {
        return sessionStrategy;
    }

    public void setSessionStrategy(SessionStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //判断是否是登录请求的url 和 是post请求
        if(StringUtils.equals("/authentication/form",request.getRequestURI())
            && StringUtils.equalsIgnoreCase(request.getMethod(),"post")){

            try {
                validate(new ServletWebRequest(request));
            }catch (ValidataCodeException ex){
                //失败的异常 把错误信息返回去,在登录失败时候实现的这个接口
                authenticationFailureHandler.onAuthenticationFailure(request,response,ex);
            return;
            }
        }
        //不是直接放行
        filterChain.doFilter(request,response);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        //从session里面拿出 放进去验证码
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, ImageCodeController.IMAGE_KEY);
        //从请求里拿到imageCode 参数的值 就是输入的验证码
        String codeInRequst = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
        if(StringUtils.isBlank(codeInRequst)){
            throw  new ValidataCodeException("验证码不能为空");
        }
        if(codeInSession == null){
            throw new ValidataCodeException("验证码不存在");
        }
        if(codeInRequst.isEmpty()){
            sessionStrategy.removeAttribute(request,ImageCodeController.IMAGE_KEY);
            throw new ValidataCodeException("验证码已过期");
        }
        if(!StringUtils.equals(codeInSession.getCode(),codeInRequst)){
            throw new ValidataCodeException("验证码不匹配");
        }
        //都验证过之后删除验证码
        sessionStrategy.removeAttribute(request,ImageCodeController.IMAGE_KEY);



    }
}
