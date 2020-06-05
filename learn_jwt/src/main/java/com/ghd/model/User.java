package com.ghd.model;

import lombok.Data;

/**
 * @author ghd-alem
 * @description
 * @date 2020/5/6 16:40
 */
@Data
public class User {

    private Integer id;
    private String username;
    private String password;
    private Byte sex;
    private String address;

}
