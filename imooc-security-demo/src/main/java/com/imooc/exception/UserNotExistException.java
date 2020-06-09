package com.imooc.exception;

import lombok.Data;

@Data
public class UserNotExistException extends RuntimeException {
    private static final Long serUID= -12341564654l;


    private String id;
    public UserNotExistException(String id) {
        super("user not exception");
        this.id=id;
    }
}
