package com.ghd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ghd-alem
 * @description
 * @date 2020/5/6 14:03
 */
@RestController
@RequestMapping("/login")
public class LoginController {


    @GetMapping("/checkUser")
    public String checkUser(){

        return null;
    }

}
