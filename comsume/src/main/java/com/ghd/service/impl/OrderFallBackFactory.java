package com.ghd.service.impl;

import com.ghd.service.OrderService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
@Component
public class OrderFallBackFactory implements FallbackFactory<OrderService> {

    @Override
    public OrderService create(Throwable throwable) {
        return new OrderService() {
            @Override
            public String getPayStatus(String orderSn) {
                return "使用fallbackFactory返回"+orderSn;
            }

            @Override
            public List<String> getPayList() {
                System.out.println("使用fallbackFactory返回");
                return Collections.emptyList();
            }

            @Override
            public List getUserList(Integer groupId) {
                System.out.println("使用fallbackFactory返回");
                return Collections.emptyList();
            }
        };
    }
}
