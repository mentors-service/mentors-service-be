package com.example.mentomen.recruit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.mentomen.recruit.dao.RecruitDAO;

@Mapper
public interface RecruitMapper {

    List<RecruitDAO> getRecruitByUserId(@Param("user_id") Long userId);

    List<RecruitDAO> getRecruitByArticleId(@Param("article_id") Integer articleId);

    int saveRecruit(@Param("user_id") Long userId, @Param("article_id") Integer articleId);

    int deleteRecruit(@Param("user_id") Long userId, @Param("article_id") Integer articleId);
}
