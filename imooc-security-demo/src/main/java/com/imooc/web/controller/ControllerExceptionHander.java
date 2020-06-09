package com.imooc.web.controller;

import com.imooc.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHander {

  //  @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    public Map<String,Object> getUserNotExistException(UserNotExistException ex){
        Map<String,Object> result = new HashMap<>();
        result.put("id",ex.getId());
        result.put("ex",ex.getMessage());
        result.put("全部",ex);
        return result;
    }

}
