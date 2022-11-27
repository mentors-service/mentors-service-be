package com.example.mentomen.article.service;

import com.example.mentomen.article.code.ArticleStatusCode;
import com.example.mentomen.article.dao.ArticleDAO;
import com.example.mentomen.article.mapper.ArticleMapper;
import com.example.mentomen.article.vo.ArticleRetriveVO;
import com.example.mentomen.article.vo.ArticleVO;
import com.example.mentomen.comment.service.CommentService;
import com.example.mentomen.comment.vo.CommentVO;
import com.example.mentomen.member.dto.UserDto;
import com.example.mentomen.member.entity.UserEntity;
import com.example.mentomen.member.repository.UserRepository;
import com.example.mentomen.recruit.service.RecruitService;
import com.example.mentomen.recruit.vo.RecruitVO;
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
  private UserRepository userRepository;

  @Autowired
  private CommentService commentService;

  @Autowired
  private RecruitService recruitService;

  @Autowired
  private ScrapService scrapService;

  @Transactional(readOnly = true)
  public List<ArticleVO> articles(
      Integer offset,
      String searchObj,
      String searchVal) {
    List<ArticleVO> articleList = new ArrayList<>();
    List<ArticleDAO> rawArticleList = articleMapper.getArticleList(
        offset,
        searchObj,
        searchVal);
    for (ArticleDAO rawArticle : rawArticleList) {
      articleList.add(articleVOBuilderFromDto(rawArticle));
    }
    return articleList;
  }

  @Transactional(readOnly = true)
  public ArticleVO findById(Integer id) {
    ArticleDAO rawArticle = articleMapper.getArticle(id);
    return articleVOBuilderFromDto(rawArticle);
  }

  public ArticleVO update(Integer id) {
    return articleMapper.updateArticle(id);
  }

  public Integer save(ArticleRetriveVO articleVO) {
    Long createrId = 2L;
    // TODO Extract UserID from token
    return articleMapper.saveArticle(articleVO.getTitle(), articleVO.getPlace(), articleVO.getStartDate(),
        articleVO.getEndDate(), articleVO.getContents(), ArticleStatusCode.ARTICLE_INPROGRESS.getCode(),
        articleVO.getTotalMember(), createrId);
  }

  public Integer delete(Integer id) {
    return articleMapper.deleteArticle(id);
  }

  private ArticleVO articleVOBuilderFromDto(ArticleDAO rawArticle) {
    UserEntity rawUser = userRepository.findById(rawArticle.getCreatorId());
    UserDto creater = UserDto.builder().name(rawUser.getUsername()).email(rawUser.getEmail())
        .picture(rawUser.getPicture()).build();
    return ArticleVO.builder().articleId(rawArticle.getArticleId())
        .createdAt(rawArticle.getCreatedAt())
        .modifiedAt(rawArticle.getModifiedAt()).title(rawArticle.getTitle()).place(rawArticle.getPlace())
        .startDate(rawArticle.getStartDate()).endDate(rawArticle.getEndDate()).contents(rawArticle.getContents())
        .creater(creater).status(ArticleStatusCode.parseCode(rawArticle.getStatus()))
        .comments(commentService.comments(rawArticle.getArticleId(), null))
        .recruit(recruitService.recruitMemberBuilder(rawArticle.getArticleId(), rawArticle.getCreatorId()))
        .scraps(scrapService.scrapBuilderByArticle(rawArticle.getArticleId(), rawArticle.getCreatorId()))
        .commentCnt(commentService.getCommentCntByArticleId(rawArticle.getArticleId()))
        .build();
  }
}
