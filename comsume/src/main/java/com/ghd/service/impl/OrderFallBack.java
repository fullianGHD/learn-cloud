package com.ghd.service.impl;

import com.ghd.service.OrderService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class OrderFallBack implements OrderService {

    @Override
    public String getPayStatus(String orderSn) {
        return "返回超时响应 Hytrix"+orderSn;
    }

    @Override
    public List<String> getPayList() {
        System.out.println("超时或异常");
        return Collections.emptyList();
    }
}