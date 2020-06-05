package com.ghd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author ghd-alem
 * @description
 * @date 2020/5/18 15:29
 */
@SpringBootApplication
@EnableAuthorizationServer
public class SecurityAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityAppApplication.class,args);
    }

    /**
     * 开启SpringSecurity用户登录认证
     * @return
     */
    @Bean
    public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
        return new WebSecurityConfigurerAdapter() {
            @Override
            public void configure(HttpSecurity httpSecurity) throws Exception {
                httpSecurity.formLogin().and().csrf().disable();
            }
        };
    }
}
