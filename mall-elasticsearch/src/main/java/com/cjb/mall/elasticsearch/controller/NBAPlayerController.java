package com.cjb.mall.elasticsearch.controller;

import com.cjb.mall.common.result.ResultUtils;
import com.cjb.mall.common.result.ResultVO;
import com.cjb.mall.elasticsearch.po.NBAPlayer;
import com.cjb.mall.elasticsearch.service.NBAPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class NBAPlayerController {

    @Autowired
    NBAPlayerService nbaPlayerService;

    @RequestMapping("/importAllPlayer")
    public ResultVO importAllPlayer(){
        try {
            nbaPlayerService.importAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultUtils.ok();
    }

    @RequestMapping("/deleteAllPlayer")
    public ResultVO deleteAllPlayer(){
        try {
            nbaPlayerService.deleteAllPlayer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultUtils.ok();
    }

    @RequestMapping("/searchMatch")
    public List<NBAPlayer> searchMatch(@RequestParam(value = "displayNameEn", required = false) String displayNameEn) {
        try {
            return nbaPlayerService.searchMatch("displayNameEn",displayNameEn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/searchTerm")
    public List<NBAPlayer> searchTerm(@RequestParam(value = "country", required = false) String country,
                                      @RequestParam(value = "teamName", required = false) String teamName) {
        try {
            if(!StringUtils.isEmpty(country))
                return nbaPlayerService.searchTerm("country",country);

            else if(!StringUtils.isEmpty(teamName))
                return nbaPlayerService.searchTerm("teamName",teamName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/searchMatchPrefix")
    public List<NBAPlayer> searchMatchPrefix(@RequestParam(value = "displayNameEn", required = false) String displayNameEn) {
        try {
            return nbaPlayerService.searchMatchPrefix("displayNameEn",displayNameEn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
