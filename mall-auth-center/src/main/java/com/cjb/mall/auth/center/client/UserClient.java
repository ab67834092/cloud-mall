package com.cjb.mall.auth.center.client;

import com.cjb.mall.user.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author chenjiabao
 * @date 2018/10/1
 */
@FeignClient(value = "user-service")
public interface UserClient extends UserApi {

}
