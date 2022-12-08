package com.example.mentomen.article.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleRetriveVO {
    private String title;
    private String place;
    private String startDate;
    private String endDate;
    private Integer totalRecruit;
    private String contents;
}
