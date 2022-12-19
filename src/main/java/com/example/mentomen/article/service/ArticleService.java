package com.example.mentomen.article.service;

import com.example.mentomen.article.code.ArticleStatusCode;
import com.example.mentomen.article.dao.ArticleDAO;
import com.example.mentomen.article.mapper.ArticleMapper;
import com.example.mentomen.article.vo.ArticleListVO;
import com.example.mentomen.article.vo.ArticleRetriveVO;
import com.example.mentomen.article.vo.ArticleVO;
import com.example.mentomen.comment.service.CommentService;
import com.example.mentomen.member.dto.UserDto;
import com.example.mentomen.member.service.UserService;
import com.example.mentomen.recruit.service.RecruitService;
import com.example.mentomen.scrap.service.ScrapService;

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

  @Autowired
  private UserService userService;

  @Autowired
  private CommentService commentService;

  @Autowired
  private RecruitService recruitService;

  @Autowired
  private ScrapService scrapService;

  @Transactional(readOnly = true)
  public ArticleListVO articlesByUserId(
      Integer offset,
      String searchObj,
      String searchVal,
      Long userId) {
    List<ArticleVO> articleList = new ArrayList<>();
    List<ArticleDAO> rawArticleList = articleMapper.getArticleList(
        offset,
        searchObj,
        searchVal,
        userId);
    for (ArticleDAO rawArticle : rawArticleList) {
      articleList.add(articleVOBuilderFromDto(rawArticle, userId));
    }
    return ArticleListVO.builder().data(articleList).currentPage(offset)
        .totalPages(Integer.valueOf(articleMapper.getArticleTotalCnt()) / 10).build();
  }

  // no Auth Article List -> TODO
  public ArticleListVO articles(
      Integer offset,
      String searchObj,
      String searchVal,
      Long userId) {
    List<ArticleVO> articleList = new ArrayList<>();
    List<ArticleDAO> rawArticleList = articleMapper.getArticleList(
        offset,
        searchObj,
        searchVal,
        null);
    for (ArticleDAO rawArticle : rawArticleList) {
      articleList.add(articleVOBuilderFromDto(rawArticle, userId));
    }
    // TODO : pagenation invaild처리 if current > totalpage 에러처리해야함.
    return ArticleListVO.builder().data(articleList).currentPage(offset)
        .totalPages(Integer.valueOf(articleMapper.getArticleTotalCnt()) / 10).build();
  }

  @Transactional(readOnly = true)
  public ArticleVO findById(Integer id, Long userId) {
    ArticleDAO rawArticle = articleMapper.getArticle(id);
    return articleVOBuilderFromDto(rawArticle, userId);
  }

  // TODO Article Status Change
  public Integer update(Integer id, ArticleRetriveVO article, Long createrId) {
    return articleMapper.updateArticle(id, article.getTitle(), article.getPlace(), article.getStartDate(),
        article.getEndDate(), article.getContents(),
        article.getTotalRecruit(), createrId);
  }

  public Integer save(ArticleRetriveVO articleVO, Long createrId) {
    // TODO Extract UserID from token
    return articleMapper.saveArticle(articleVO.getTitle(), articleVO.getPlace(), articleVO.getStartDate(),
        articleVO.getEndDate(), articleVO.getContents(), ArticleStatusCode.ARTICLE_INPROGRESS.getCode(),
        articleVO.getTotalRecruit(), createrId);
  }

  public Integer delete(Integer id, Long createrId) {
    return articleMapper.deleteArticle(id, createrId);
  }

  private ArticleVO articleVOBuilderFromDto(ArticleDAO rawArticle, Long userId) {
    UserDto rawUser = userService.findById(rawArticle.getCreatorId());
    return ArticleVO.builder().articleId(rawArticle.getArticleId())
        .createdAt(rawArticle.getCreatedAt())
        .modifiedAt(rawArticle.getModifiedAt()).title(rawArticle.getTitle()).place(rawArticle.getPlace())
        .startDate(rawArticle.getStartDate()).endDate(rawArticle.getEndDate()).contents(rawArticle.getContents())
        .creater(rawUser).status(ArticleStatusCode.parseCode(rawArticle.getStatus()))
        .comments(commentService.comments(rawArticle.getArticleId(), null))
        .recruit(recruitService.recruitMemberBuilder(rawArticle.getArticleId(), userId))
        .totalRecruit(rawArticle.getTotalRecruit())
        .scraps(scrapService.scrapBuilderByArticle(rawArticle.getArticleId(), userId))
        .commentCnt(commentService.getCommentCntByArticleId(rawArticle.getArticleId()))
        .build();
  }
}
