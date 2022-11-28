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
    public String updateRecruitStatus(Integer articleId, Long userId) {
        if (delete(articleId, userId) == 0) {
            save(articleId, userId);
            return "Create Scrap";
        } else {
            return "Delete Scrap";
        }
    }

    private Integer save(Integer articleId, Long userId) {
        return recruitMapper.saveRecruit(userId, articleId);
    }

    private Integer delete(Integer articleId, Long userId) {
        return recruitMapper.deleteRecruit(userId, articleId);
    }

    public RecruitVO recruitMemberBuilder(Integer articleId, Long userId) {
        List<RecruitDAO> rawRecruitList = recruitMapper.getRecruitByArticleId(articleId);
        List<Long> createrIdList = new ArrayList<>();
        Boolean isJoined = false;
        for (RecruitDAO rawRecruit : rawRecruitList) {
            createrIdList.add(rawRecruit.getJoinUserId());
            if (rawRecruit.getJoinUserId() == userId) {
                isJoined = true;
            }
        }
        return RecruitVO.builder().isRecruited(isJoined)
                .createrIdList(createrIdList).joinCnt(rawRecruitList.size()).build();
    }
    // TODO Add Article Status Check Logic
}
