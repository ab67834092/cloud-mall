package com.cjb.mall.controller;

import com.cjb.mall.caffeine.template.CaffeineTemplate;
import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jws.Oneway;

@RestController
public class CaffeineController {

    @Autowired
    CaffeineTemplate caffeineTemplate;

    @Resource(name = "productCache")
    private Cache productCache;

    @RequestMapping("test1")
    public Object test1(){
        productCache.put("1","lala1");
        return "success";
    }

    @RequestMapping("test2")
    public Object test2(){
        productCache.put("2","lala2");
        return "success";
    }

    @RequestMapping("test3")
    public Object test3(){
        System.out.println(productCache.getIfPresent("1"));
        System.out.println(productCache.getIfPresent("2"));
        return "success";
    }
}
