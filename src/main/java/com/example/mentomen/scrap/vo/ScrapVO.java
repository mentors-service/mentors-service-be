package com.example.mentomen.scrap.vo;

import com.example.mentomen.article.vo.CreaterVO;

import lombok.Data;

@Data
public class ScrapVO {
    private String articleId;
    private Integer scrapCnt;
    private CreaterVO createrVO;
}
