package com.ghd.service.impl;

import com.ghd.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public String queryUser() {
        return "查询用户";
    }
}
