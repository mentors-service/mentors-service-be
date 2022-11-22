package com.example.mentomen.article.vo;

import com.example.mentomen.article.code.ArticleStatusCode;
import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

@Getter
@Builder
public class ArticleVO {

  private Integer articleId;
  private CreaterVO creater;
  private Date createdAt;
  private Date modifiedAt;
  private String title;
  private String place;
  private String contents;
  private MemberVO member;
  private ScrapVO scraps;

  @Singular("comment")
  private List<CommentVO> comments;

  private Integer commentCnt; // 댓글 + 대댓글.
  private ArticleStatusCode status;
}
