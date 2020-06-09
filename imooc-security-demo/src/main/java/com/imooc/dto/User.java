package com.imooc.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    public interface UsersimpleView{};

    public interface UserdataView extends UsersimpleView{};

    @JsonView(UsersimpleView.class)
    private int id;
    private String name;
    private String password;
    @JsonView(UserdataView.class)
    private Date data;

}
