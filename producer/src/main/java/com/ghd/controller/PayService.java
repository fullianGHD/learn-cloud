package com.ghd.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
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

    @GetMapping("/getUserList")
    public List getUserList(@RequestParam(value = "groupId") Integer groupId){
        if(!Objects.isNull(groupId)){
            ArrayList<Object> list = new ArrayList<>();
            list.add("1");
            list.add("2");
            list.add("3");
            list.add("4");
            return list;
        }
        return Collections.emptyList();
    }
}
