package com.ghd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ghd.dao")
public class MiaoSellApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiaoSellApplication.class, args);
    }
}
