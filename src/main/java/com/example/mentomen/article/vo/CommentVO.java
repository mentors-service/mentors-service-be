package com.example.mentomen.article.vo;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentVO {
    private CreaterVO creater;
    private Date createdAt;
    private String content;
    private List<CommentVO> childs;
}
