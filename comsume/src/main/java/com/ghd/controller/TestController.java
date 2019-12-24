package com.ghd.controller;

import com.ghd.service.OrderService;
import com.ghd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;

    @GetMapping("/payStatus/{orderSn}")
    public String payStatus(@PathVariable("orderSn") String orderSn){
        System.out.println(orderSn);
        return orderService.getPayStatus(orderSn);
    }

    @GetMapping("/getPayList")
    public List getPayList(){
        return orderService.getPayList();
    }


    @GetMapping("/getUser")
    public String getUser(){
        return userService.queryUser();
    }
}
