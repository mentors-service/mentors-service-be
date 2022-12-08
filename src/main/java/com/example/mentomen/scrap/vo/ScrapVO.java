package com.example.mentomen.scrap.vo;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScrapVO {
    private Boolean isScraped;
    private Integer articleId;
    private Integer scrapCnt;
    private List<Long> createrIdList;
}
