package com.ghd.config;


import com.ghd.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author ghd-alem
 * @description
 * @date 2020/5/6 17:24
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册登录拦截器，拦截除/login以外的所有请求
        registry.addInterceptor(new AuthenticationInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/login/loginUser");
    }
}
