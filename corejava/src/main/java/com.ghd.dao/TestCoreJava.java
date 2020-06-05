package com.ghd.dao;

import lombok.Data;
import lombok.ToString;

@Data
public class TestCoreJava {

    private String name;
    private Boolean sex;
    public int age;


    public String returnName(){
        return "获取字符串";
    }

    private String returnParams(String str1,String str2){
        return str1+str2;
    }


}
