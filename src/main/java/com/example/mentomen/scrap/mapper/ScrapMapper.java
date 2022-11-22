package com.example.mentomen.scrap.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.mentomen.scrap.dao.ScrapDAO;

@Mapper
public interface ScrapMapper {

    List<ScrapDAO> getScrapByUserId(@Param("user_id") Integer userId);

    List<ScrapDAO> getScrapByArticleId(@Param("article_id") Integer articleId);

    int saveScrap(@Param("user_id") Integer userId, @Param("article_id") Integer articleId);

    int deleteScrap(@Param("user_id") Integer userId, @Param("article_id") Integer articleId);
}
