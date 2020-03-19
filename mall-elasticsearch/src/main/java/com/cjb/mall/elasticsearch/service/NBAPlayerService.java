package com.cjb.mall.elasticsearch.service;

import com.cjb.mall.elasticsearch.po.NBAPlayer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface NBAPlayerService {

    void importAll() throws IOException;

    void addPlayer(NBAPlayer nbaPlayer, String id) throws IOException;

    Map<String,Object> getPlayer(String id) throws IOException;

    boolean updatePlayer(NBAPlayer player,String id) throws IOException;

    public boolean deletePlayer(String id) throws IOException;

    public boolean deleteAllPlayer() throws IOException;

    void deleteIndex() throws IOException;


    List<NBAPlayer> searchMatch(String key, String value)throws IOException;

    //关键字匹配
    List<NBAPlayer> searchTerm(String key,String value) throws IOException;

    //带有指定前缀term的文档
    List<NBAPlayer> searchMatchPrefix(String key,String value) throws IOException;
}
