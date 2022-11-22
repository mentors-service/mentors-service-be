package com.example.mentomen.scrap.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.mentomen.scrap.vo.ScrapVO;

@Mapper
public interface ScrapMapper {

    ScrapVO getScrapArticleByUserId(@Param("comment_id") Integer commentId);

    ScrapVO getScrapByUserId(@Param("comment_id") Integer commentId);

    int saveScrap();

    int deleteScrap(@Param("scrap_id") Integer commentId);
}
