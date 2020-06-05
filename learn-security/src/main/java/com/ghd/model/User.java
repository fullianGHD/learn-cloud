package com.ghd.model;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.validation.constraints.NotEmpty;

/**
 * @author ghd-alem
 * @description
 * @date 2020/5/15 11:24
 */
@Data
public class User {


    public interface login{}

    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty(message = "分组",groups = {login.class})
    private String password;

    private String address;

    private byte sex;

    private Integer age;

}
