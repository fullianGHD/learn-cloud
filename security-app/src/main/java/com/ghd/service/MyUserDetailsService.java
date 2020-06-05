/*
package com.ghd.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

*/
/**
 * @author ghd-alem
 * @description
 * @date 2020/5/21 17:27
 *//*

@Slf4j
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登陆用户名："+username);
        if(!username.equals("plpl")){
            throw new UsernameNotFoundException("账号不符");
        }
        //根据用户名查询用户信息
        log.info("加密后的密码："+passwordEncoder.encode("123456"));
        //根据查找到的用户信息判断是否为冻结用户
        return new User(username,passwordEncoder.encode("123456"),
                //是否可用
                true,
                //是否过期
                false,
                //密码是否过期
                false,
                //是否被锁定
                false,
                //用户权限list
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin，ROLE_USER")
        );
    }
}
*/
