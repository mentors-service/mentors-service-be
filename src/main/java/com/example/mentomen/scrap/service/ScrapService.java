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
    public String updateScrapStatus(Integer articleId, Long userId) {
        if (delete(articleId, userId) == 0) {
            save(articleId, userId);
            return "Create Scrap";
        } else {
            return "Delete Scrap";
        }
    }

    private Integer save(Integer articleId, Long userId) {
        return scrapMapper.saveScrap(userId, articleId);
    }

    private Integer delete(Integer articleId, Long userId) {
        return scrapMapper.deleteScrap(userId, articleId);
    }

    public ScrapVO scrapBuilderByArticle(Integer articleId, Long userId) {
        List<ScrapDAO> rawScrapList = scrapMapper.getScrapByArticleId(articleId);
        List<Long> createrIdList = new ArrayList<>();
        Boolean isScraped = false;
        for (ScrapDAO rawScrap : rawScrapList) {
            createrIdList.add(rawScrap.getScrapUserId());
            if (rawScrap.getScrapUserId() == userId) {
                isScraped = true;
            }
        }
        return ScrapVO.builder().articleId(articleId).isScraped(isScraped)
                .createrIdList(createrIdList).scrapCnt(rawScrapList.size()).build();
    }
}
