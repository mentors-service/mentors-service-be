package com.example.mentomen.comment.vo;

import lombok.Getter;

@Getter
public class CommentRetriveVO {
    private Integer articleId;
    private Integer parentId;
    private String contents;
}
