package com.ghd.controller;

import com.ghd.model.User;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author ghd-alem
 * @description
 * @date 2020/5/14 16:01
 */
@RestController
@RequestMapping("/restFul")
public class RestFulAPI {

    @GetMapping("/get")
    public Object get(){
        return "get请求";
    }

    @PostMapping("/post")
    public Object post(){
        return "post请求";
    }

    @DeleteMapping("/delete")
    public Object delete(){
        return "delete请求";
    }

    @PostMapping("/save")
    public Object save(@Validated({User.login.class}) User user, BindingResult error){
        if(error.hasErrors()){
            error.getAllErrors().stream().peek(e-> System.out.println(e));
        }
        System.out.println(user);
        return user;
    }

}
