package com.example.mentomen.recruit.vo;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecruitVO {
    private Integer articleId;
    private Integer joinCnt;
    private List<Integer> createrIdList;
}
