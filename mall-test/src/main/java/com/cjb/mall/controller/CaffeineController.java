package com.cjb.mall.controller;

import com.cjb.mall.caffeine.template.CaffeineTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.Oneway;

@RestController
public class CaffeineController {

    @Autowired
    CaffeineTemplate caffeineTemplate;

    @RequestMapping("test")
    public Object test(){
        Object productDOCache = caffeineTemplate.getProductDOCache("123");
        return productDOCache;
    }
}
