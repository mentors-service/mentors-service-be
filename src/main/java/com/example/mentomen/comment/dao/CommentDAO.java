package com.example.mentomen.comment.dao;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class CommentDAO {
    private Integer commentId;
    private Integer articleId;
    private Long creatorId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss z", timezone = "Asia/Seoul")
    private Date createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss z", timezone = "Asia/Seoul")
    private Date modifiedAt;
    private String contents;
    private Integer parentId;
    private Integer childId;
}
