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
}
