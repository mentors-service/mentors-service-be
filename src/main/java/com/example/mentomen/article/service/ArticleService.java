package com.example.mentomen.article.service;

import com.example.mentomen.article.mapper.ArticleMapper;
import com.example.mentomen.article.vo.ArticleVO;
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
    String orderBy
  ) {
    return articleMapper.getArticleList(
      limit,
      offset,
      searchObj,
      searchVal,
      orderObj,
      orderBy
    );
  }

  @Transactional(readOnly = true)
  public ArticleVO findById(Integer id) {
    return articleMapper.getArticle(id);
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
