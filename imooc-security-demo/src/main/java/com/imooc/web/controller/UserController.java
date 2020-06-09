package com.imooc.web.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.exception.UserNotExistException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    @JsonView(User.UsersimpleView.class)
    public User getuser(){
        User user = new User();
        user.setId(1);
        user.setName("张三");
        return user;
    }
    //id必须是数字
    //正则表达式
    @JsonView(User.UserdataView.class)
    @GetMapping("/{id:\\d+}")
    public User getuser1(){
        User user = new User();
        user.setId(1);
        user.setName("张三");
        throw new UserNotExistException("1");
        //return user;
    }

    //校验是否为空
    @PostMapping
    public User cerate(@Valid @RequestBody User user , BindingResult errors){
        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(n -> {
                System.out.println(n.getDefaultMessage());
            });
        }
        System.out.println(user.getId());
        System.out.println(user.getName());
        System.out.println(user.getPassword());
        System.out.println(user.getData());
        user.setId(1);
        return  user;
    }





}
