package com.imooc.security.core;

public class BrowserProperties {

    private String  loginPage = "/imooc-signIn.html";

    private LoginType loginType ;

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
