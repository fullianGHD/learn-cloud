package com.ghd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {


    @GetMapping("/kill")
    public Object kill(Integer a ){
        return null;
    }


}
