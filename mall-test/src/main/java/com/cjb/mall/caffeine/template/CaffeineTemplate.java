package com.cjb.mall.caffeine.template;

public interface CaffeineTemplate {

    public Object getProductDOCache(String skuCode);

    public Object getUserDOCache(String key);
}
