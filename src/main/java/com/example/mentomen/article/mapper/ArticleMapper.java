package com.example.mentomen.article.mapper;

import com.example.mentomen.article.dao.ArticleDAO;
import com.example.mentomen.article.vo.ArticleVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleMapper {
  List<ArticleDAO> getArticleList(
      @Param("limit") Integer limit,
      @Param("offset") Integer offset,
      @Param("searchObj") String searchObj,
      @Param("searchVal") String searchVal,
      @Param("orderObj") String orderObj,
      @Param("orderBy") String orderBy);

  ArticleDAO getArticle(@Param("id") Integer id);

  ArticleVO updateArticle(@Param("id") Integer id);

  int saveArticle();

  int deleteArticle(@Param("articleId") Integer adaptorId);
}
