package com.ghd.service;

import com.ghd.model.User;
import com.ghd.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author ghd-alem
 * @description
 * @date 2020/5/6 17:13
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User findUserById(Integer id) {
        User user = new User();
        if(id == 1){
            user.setId(1);
            user.setUsername("xiaohei");
            user.setPassword("123456");
            user.setAddress("北京");
            user.setSex((byte)0);
        }
        if(id ==2){
            user.setId(2);
            user.setUsername("xiaohong");
            user.setPassword("456789");
            user.setAddress("上海");
            user.setSex((byte)1);
        }
        return user;
    }

    @Override
    public User findUserUnmPwd(String username, String password) {
        User user = new User();
        if(username.equals("qqqqq")&& password.equals("123456")){
            user.setId(1);
            user.setUsername("qqqqq");
            user.setPassword("123456");
            user.setAddress("北京");
            user.setSex((byte)0);
            return user;
        }else if(username.equals("wwwwww") && password.equals("456789")){
            user.setId(2);
            user.setUsername("wwwwww");
            user.setPassword("456789");
            user.setAddress("上海");
            user.setSex((byte)1);
            return user;
        }else if(username.equals("eeeee") && password.equals("123789")){
            user.setId(3);
            user.setUsername("eeeee");
            user.setPassword("123789");
            user.setAddress("广州");
            user.setSex((byte)0);
            return user;
        }
        return null;
    }


}
