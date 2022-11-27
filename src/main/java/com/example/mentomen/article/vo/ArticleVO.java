package com.example.mentomen.article.vo;

import com.example.mentomen.article.code.ArticleStatusCode;
import com.example.mentomen.comment.vo.CommentVO;
import com.example.mentomen.member.dto.UserDto;
import com.example.mentomen.recruit.vo.RecruitVO;
import com.example.mentomen.scrap.vo.ScrapVO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.ALWAYS)
public class ArticleVO {

  private Integer articleId;
  private UserDto creater;
  private Date createdAt;
  private Date modifiedAt;
  private String title;
  private String place;
  private String startDate;
  private String endDate;
  private String contents;
  private ScrapVO scraps;
  private RecruitVO recruit;
  private List<CommentVO> comments;

  private Integer commentCnt; // 댓글 + 대댓글.
  private ArticleStatusCode status;
}
