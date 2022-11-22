package com.example.mentomen.comment.vo;

import java.util.Date;
import java.util.List;

import com.example.mentomen.article.vo.CreaterVO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentVO {
    private Integer commentId;
    private CreaterVO creater;
    private Date createdAt;
    private Date modifiedAt;
    private String content;
    private List<CommentVO> childs;
}
