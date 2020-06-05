package com.ghd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author ghd-alem
 * @description
 * @date 2020/5/18 16:01
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig {

    /**
     * 密码加密
     * 默认的加密，也可以自定义自己的加密 返回自定义加密方法
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
