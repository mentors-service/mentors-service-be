package com.example.mentomen.recruit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mentomen.recruit.dao.RecruitDAO;
import com.example.mentomen.recruit.mapper.RecruitMapper;
import com.example.mentomen.recruit.vo.RecruitVO;

@Service
public class RecruitService {

    @Autowired
    private RecruitMapper recruitMapper;

    // TODO Update Error Exception
    public String updateRecruitStatus(Integer articleId, Integer userId) {
        if (delete(articleId, userId) == 0) {
            save(articleId, userId);
            return "Create Scrap";
        }
        return null;
    }

    private Integer save(Integer articleId, Integer userId) {
        return recruitMapper.saveRecruit(articleId, userId);
    }

    private Integer delete(Integer articleId, Integer userId) {
        return recruitMapper.deleteRecruit(articleId, userId);
    }

    public RecruitVO recruitMemberBuilder(Integer articleId) {
        List<RecruitDAO> rawRecruitList = recruitMapper.getRecruitByArticleId(articleId);
        List<Integer> createrIdList = new ArrayList<>();
        for (RecruitDAO rawRecruit : rawRecruitList) {
            createrIdList.add(rawRecruit.getJoinUserId());
        }
        return RecruitVO.builder().articleId(articleId)
                .createrIdList(createrIdList).joinCnt(rawRecruitList.size()).build();
    }
}
