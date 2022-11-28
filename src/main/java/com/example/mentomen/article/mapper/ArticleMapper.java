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
                        @Param("searchVal") String searchVal,
                        @Param("createrId") Long createrId);

        ArticleDAO getArticle(@Param("articleId") Integer id);

        int updateArticle(@Param("articleId") Integer id,
                        @Param("title") String title,
                        @Param("place") String place,
                        @Param("startDate") String startDate,
                        @Param("endDate") String endDate,
                        @Param("contents") String contents,
                        @Param("status") String status,
                        @Param("totalMember") Integer totalMember,
                        @Param("createrId") Long createrId);

        int saveArticle(
                        @Param("title") String limit,
                        @Param("place") String place,
                        @Param("startDate") String startDate,
                        @Param("endDate") String endDate,
                        @Param("contents") String contents,
                        @Param("status") String status,
                        @Param("totalMember") Integer totalMember,
                        @Param("userId") Long userId);

        int deleteArticle(@Param("articleId") Integer adaptorId, @Param("createrId") Long createrId);
}
