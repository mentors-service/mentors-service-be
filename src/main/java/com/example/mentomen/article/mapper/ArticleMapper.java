package com.example.mentomen.article.mapper;

import com.example.mentomen.article.dao.ArticleDAO;
import com.example.mentomen.article.vo.ArticleVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleMapper {
  List<ArticleDAO> getArticleList(
      @Param("offset") Integer offset,
      @Param("searchObj") String searchObj,
      @Param("searchVal") String searchVal);

  ArticleDAO getArticle(@Param("id") Integer id);

  ArticleVO updateArticle(@Param("id") Integer id);

  int saveArticle(
      @Param("title") String limit,
      @Param("place") String place,
      @Param("startDate") String startDate,
      @Param("endDate") String endDate,
      @Param("contents") String contents,
      @Param("status") String status,
      @Param("totalMember") Integer totalMember,
      @Param("userId") Long userId);

  int deleteArticle(@Param("articleId") Integer adaptorId);
}
