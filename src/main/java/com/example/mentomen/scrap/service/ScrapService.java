package com.example.mentomen.scrap.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mentomen.scrap.dao.ScrapDAO;
import com.example.mentomen.scrap.mapper.ScrapMapper;
import com.example.mentomen.scrap.vo.ScrapVO;

@Service
public class ScrapService {

    @Autowired
    private ScrapMapper scrapMapper;

    // TODO Update Error Exception
    public String updateScrapStatus(Integer articleId, Integer userId) {
        if (delete(articleId, userId) == 0) {
            save(articleId, userId);
            return "Create Scrap";
        }
        return null;
    }

    private Integer save(Integer articleId, Integer userId) {
        return scrapMapper.saveScrap(articleId, userId);
    }

    private Integer delete(Integer articleId, Integer userId) {
        return scrapMapper.deleteScrap(articleId, userId);
    }

    public ScrapVO scrapBuilderByArticle(Integer articleId) {
        List<ScrapDAO> rawScrapList = scrapMapper.getScrapByArticleId(articleId);
        List<Integer> createrIdList = new ArrayList<>();
        for (ScrapDAO rawScrap : rawScrapList) {
            createrIdList.add(rawScrap.getScrapUserId());
        }
        return ScrapVO.builder().articleId(articleId)
                .createrIdList(createrIdList).scrapCnt(rawScrapList.size()).build();
    }
}
