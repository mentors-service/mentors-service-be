package com.example.mentomen.article.service;

import com.example.mentomen.article.dao.ArticleDAO;
import com.example.mentomen.article.mapper.ArticleMapper;
import com.example.mentomen.article.vo.ArticleVO;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticleService {

  @Autowired
  private ArticleMapper articleMapper;

  @Transactional(readOnly = true)
  public List<ArticleVO> articles(
      Integer limit,
      Integer offset,
      String searchObj,
      String searchVal,
      String orderObj,
      String orderBy) {
    List<ArticleVO> articleList = new ArrayList<>();
    List<ArticleDAO> rawArticleList = articleMapper.getArticleList(
        limit,
        offset,
        searchObj,
        searchVal,
        orderObj,
        orderBy);
    for (ArticleDAO rawArticle : rawArticleList) {
      ArticleVO article = ArticleVO.builder().articleId(rawArticle.getArticleId()).build();
      articleList.add(article);
    }
    return articleList;
  }

  @Transactional(readOnly = true)
  public ArticleVO findById(Integer id) {
    ArticleDAO rawArticle = articleMapper.getArticle(id);
    return ArticleVO.builder().articleId(rawArticle.getArticleId()).build();
  }

  public ArticleVO update(Integer id) {
    return articleMapper.updateArticle(id);
  }

  public Integer save(ArticleVO articleVO) {
    return articleMapper.saveArticle();
  }

  public Integer delete(Integer id) {
    return articleMapper.deleteArticle(id);
  }
}
