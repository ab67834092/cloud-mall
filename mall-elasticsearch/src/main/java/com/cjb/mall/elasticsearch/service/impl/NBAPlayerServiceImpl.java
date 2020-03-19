package com.cjb.mall.elasticsearch.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cjb.mall.elasticsearch.mapper.NBAPlayerMapper;
import com.cjb.mall.elasticsearch.po.NBAPlayer;
import com.cjb.mall.elasticsearch.service.NBAPlayerService;
import net.sf.jsqlparser.statement.create.table.Index;
import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NBAPlayerServiceImpl implements NBAPlayerService {

    @Autowired
    NBAPlayerMapper nbaPlayerMapper;
    @Autowired
    RestHighLevelClient client;

    @Override
    public void importAll() throws IOException {
        List<NBAPlayer> nbaPlayers = nbaPlayerMapper.selectAll();
        if(!CollectionUtils.isEmpty(nbaPlayers)){
            for(NBAPlayer nbaPlayer:nbaPlayers){
                addPlayer(nbaPlayer,nbaPlayer.getPlayerId());
            }
        }
    }

    @Override
    public void addPlayer(NBAPlayer nbaPlayer, String id) throws IOException {
        IndexRequest indexRequest = new IndexRequest("nba").id(id).source(beanToMap(nbaPlayer));
        client.index(indexRequest, RequestOptions.DEFAULT);
    }

    @Override
    public Map<String, Object> getPlayer(String id) throws IOException {
        GetRequest getRequest = new GetRequest("nba",id);
        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
        return response.getSource();
    }

    @Override
    public boolean updatePlayer(NBAPlayer player,String id) throws IOException {
        UpdateRequest request = new UpdateRequest("nba",id).doc(beanToMap(player));
        UpdateResponse response = client.update(request,RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSON(response));
        return true;
    }

    @Override
    public boolean deletePlayer(String id) throws IOException {
        DeleteRequest request = new DeleteRequest("nba",id);
        DeleteResponse response = client.delete(request,RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSON(response));
        return true;
    }

    @Override
    public boolean deleteAllPlayer() throws IOException {
        DeleteByQueryRequest request = new DeleteByQueryRequest("nba");
        BulkByScrollResponse response = client.deleteByQuery(request, RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSON(response));
        return true;
    }

    public void deleteIndex() throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("nba");
        AcknowledgedResponse response = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSON(response));
    }

    public List<NBAPlayer> searchMatch(String key,String value) throws IOException {
        SearchRequest searchRequest = new SearchRequest("nba");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery(key,value));
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(100);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = client.search(searchRequest,RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSON(response));

        SearchHit[] hits = response.getHits().getHits();
        List<NBAPlayer> playerList = new ArrayList<>();
        for(SearchHit hit: hits){
            NBAPlayer player = JSONObject.parseObject(hit.getSourceAsString(),NBAPlayer.class);
            playerList.add(player);
        }

        return playerList;
    }


    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                if (beanMap.get(key) != null)
                    map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }
}
