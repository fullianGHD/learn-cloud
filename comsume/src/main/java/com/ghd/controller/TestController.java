package com.ghd.controller;

import com.ghd.service.OrderService;
import com.ghd.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    @GetMapping("/getUserList")
    public List getUserList(Integer groupId){
        return orderService.getUserList(groupId);
    }


    public String fallbackMethod(){
        return "调用超时返回的结果";
    }


}
