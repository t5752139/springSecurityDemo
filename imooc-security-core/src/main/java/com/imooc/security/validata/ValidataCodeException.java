package com.imooc.security.validata;


import org.springframework.security.core.AuthenticationException;

import java.io.Serializable;

public class ValidataCodeException extends AuthenticationException implements Serializable {




    public ValidataCodeException(String msg) {
        super(msg);
    }
}
