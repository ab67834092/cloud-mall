package com.cjb.mall.elasticsearch;

import com.alibaba.fastjson.JSONObject;
import com.cjb.mall.elasticsearch.po.NBAPlayer;
import com.cjb.mall.elasticsearch.service.NBAPlayerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MallElasticsearchApplicationTests {

    @Autowired
    NBAPlayerService nbaPlayerService;

    @Test
    public void addPlayer() throws IOException {
        NBAPlayer player = new NBAPlayer();
        player.setDisplayName("杨超越");
        player.setId(999);
        nbaPlayerService.addPlayer(player,"999");
    }

    @Test
    public void getPlayer() throws IOException {
        Map<String, Object> player =  nbaPlayerService.getPlayer("999");
        System.out.println(JSONObject.toJSON(player));
    }

    @Test
    public void updatePlayer() throws IOException {
        NBAPlayer player = new NBAPlayer();
        player.setDisplayName("杨超越111");
        nbaPlayerService.updatePlayer(player,"999");
    }

    @Test
    public void deletePlayer() throws IOException {
        nbaPlayerService.deletePlayer("999");
    }

    @Test
    public void deleteAllPlayer() throws IOException {
        nbaPlayerService.deleteAllPlayer();
    }

    @Test
    public void deleteIndex() throws IOException {
        nbaPlayerService.deleteIndex();
    }



}
