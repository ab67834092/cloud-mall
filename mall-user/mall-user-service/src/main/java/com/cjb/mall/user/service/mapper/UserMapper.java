package com.cjb.mall.user.service.mapper;

import com.cjb.mall.user.service.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User getUserByParam(User user);
}
