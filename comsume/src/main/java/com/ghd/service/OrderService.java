package com.ghd.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
@FeignClient(name = "producter-client")
public interface OrderService {

    @GetMapping("pay/getPayStatus/{orderSn}")
    String getPayStatus(@PathVariable("orderSn") String orderSn);

    @GetMapping("pay/getPayList")
    List<String> getPayList();
}
