package com.ghd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/pay")
public class PayService {


    @GetMapping("/getPayStatus/{orderSn}")
    public String getPayStatus(@PathVariable("orderSn") String orderSn){
        if(!Objects.isNull(orderSn)){
            return "订单号："+orderSn+" 支付成功";
        }
        return "订单号非法";
    }


    @GetMapping("/getPayList")
    public List<String> getPayList(){
        List<String> list = new ArrayList<>();
        list.add("asd");
        list.add("qwe");
        list.add("zxc");
        return list;
    }

}
