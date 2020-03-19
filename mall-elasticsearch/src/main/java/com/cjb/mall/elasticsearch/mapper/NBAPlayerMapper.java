package com.cjb.mall.elasticsearch.mapper;

import com.cjb.mall.elasticsearch.po.NBAPlayer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NBAPlayerMapper {

    List<NBAPlayer> selectAll();
}
