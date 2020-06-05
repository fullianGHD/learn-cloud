package com.ghd.model;

import lombok.Data;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@PropertySource("classpath:application.properties")
@Component
public class User {

    private String name;
}
