package com.cjb.mall.caffeine.template;

import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CaffeineTemplateImpl implements CaffeineTemplate {

    @Resource(name = "productCache")
    private Cache productCache;

    @Resource(name = "userCache")
    private Cache userCache;

//    @Autowired
//    private RedisCache redisCache;
//
//    @Autowired
//    private ProductClient productClient;

    public Object getProductDOCache(String skuCode){
        //从caffeine中获取
        Object caffeineObj = productCache.getIfPresent(skuCode);
        if(caffeineObj!=null){
            return caffeineObj;
        }
//        //这里说明没有从Caffeine获取到，去查询redis
//        Object redisObj = redisCache.get(skuCode);
//        if(redisObj!=null){
//            //将查询放到Caffeine中
//            productCache.put(skuCode,redisObj);
//            return redisObj;
//        }
//        //防止羊群效应 缓存穿透
//        //可以看下是否有必要加锁，毕竟数据库不在咱们这端，个人感觉加上没什么坏处
//        if(tryLock(skuCode)){
//            //从caffeine中获取
//            Object caffeineObj2 = productCache.getIfPresent(skuCode);
//            if(caffeineObj2!=null){
//                return caffeineObj2;
//            }
//            //这里说明没有从Caffeine获取到，去查询redis
//            Object redisObj2 = redisCache.get(skuCode);
//            if(redisObj2!=null){
//                //将查询放到Caffeine中
//                productCache.put(skuCode,redisObj2);
//                return redisObj2;
//            }
//            //这里说明redis没有值，需要远程调用商品接口获取
//            Object product = productClient.get(skuCode);
//            if(product!=null){
//                //将查询放到redis中
//                redisCache.set(skuCode,product);
//                //将查询放到Caffeine中
//                productCache.put(skuCode,redisObj);
//                return product;
//            }
//        }
        return null;
    }

    public Object getUserDOCache(String key){
        System.out.println(userCache);
        return null;
    }
}
