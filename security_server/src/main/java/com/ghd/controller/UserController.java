package com.ghd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ghd-alem
 * @description
 * @date 2020/5/6 14:08
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getUserInfo")
    public String getUserInfo(){
        return "Api user/getUserInfo  [getUserInfo]";
    }


}
