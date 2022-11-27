package com.example.mentomen.article.dao;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class ArticleDAO {
    private Integer articleId;
    private Long creatorId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss z", timezone = "Asia/Seoul")
    private Date createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss z", timezone = "Asia/Seoul")
    private Date modifiedAt;
    private String title;
    private String place;
    private String contents;
    private String startDate;
    private String endDate;
    private String status;
    private Integer totalRecruit;
}
