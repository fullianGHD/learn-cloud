package com.ghd.service;

import com.ghd.model.User;

/**
 * @author ghd-alem
 * @description
 * @date 2020/5/6 17:12
 */
public interface UserService {

    User findUserById(Integer id);

    User findUserUnmPwd(String username,String password);
}
