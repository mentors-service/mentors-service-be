package com.example.mentomen.scrap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mentomen.scrap.mapper.ScrapMapper;
import com.example.mentomen.scrap.vo.ScrapVO;

@Service
public class ScrapService {

    @Autowired
    private ScrapMapper scrapMapper;

    private Boolean isScrapByUser(Integer articleId, Integer userId) {
        return scrapMapper.getArticle(id);
    }

    public Integer updateScrapStatus(Integer articleId, Integer userId, ScrapVO scrapVO) {
        if (isScrapByUser(articleId, userId)) {
            return delete(articleId);
        } else
            return save(scrapVO);
    }

    private Integer save(ScrapVO scrapVO) {
        return scrapMapper.saveScrap();
    }

    private Integer delete(Integer id) {
        return scrapMapper.deleteScrap(id);
    }
}
