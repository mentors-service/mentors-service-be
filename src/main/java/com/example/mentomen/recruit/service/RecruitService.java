package com.example.mentomen.recruit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mentomen.article.code.ArticleStatusCode;
import com.example.mentomen.article.mapper.ArticleMapper;
import com.example.mentomen.recruit.dao.RecruitDAO;
import com.example.mentomen.recruit.mapper.RecruitMapper;
import com.example.mentomen.recruit.vo.RecruitVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecruitService {

    @Autowired
    private RecruitMapper recruitMapper;

    @Autowired
    private ArticleMapper articleMapper;

    // TODO Update Error Exception
    public String updateRecruitStatus(Integer articleId, Long userId) {
        if (delete(articleId, userId) == 0) {
            if (!checkFullMember(articleId)) {
                save(articleId, userId);
                return "Create Recruit";
            } else {
                return "Already Full Member";
            }
        } else {
            articleMapper.updateArticleStatus(articleId, ArticleStatusCode.ARTICLE_INPROGRESS.getCode());
            return "Delete Recruit";
        }
    }

    private Boolean checkFullMember(Integer articleId) {
        if (articleMapper.getArticle(articleId).getTotalRecruit() == recruitMapper.getRecruitByArticleId(articleId)
                .size()) {
            articleMapper.updateArticleStatus(articleId, ArticleStatusCode.ARTICLE_COMPLETED.getCode());
            return true;
        } else
            return false;
    }

    // TODO : Creater Check Not Recruit
    private Integer save(Integer articleId, Long userId) {
        return recruitMapper.saveRecruit(userId, articleId);
    }

    private Integer delete(Integer articleId, Long userId) {
        return recruitMapper.deleteRecruit(userId, articleId);
    }

    public RecruitVO recruitMemberBuilder(Integer articleId, Long userId) {
        List<RecruitDAO> rawRecruitList = recruitMapper.getRecruitByArticleId(articleId);
        Boolean isJoined = false;
        if (recruitMapper.getRecruitByArticleIdAndUserId(articleId, userId).size() == 1) {
            isJoined = true;
        }
        List<Long> createrIdList = new ArrayList<>();
        for (RecruitDAO rawRecruit : rawRecruitList) {
            createrIdList.add(rawRecruit.getJoinUserId());
        }
        return RecruitVO.builder().isRecruited(isJoined)
                .createrIdList(createrIdList).joinCnt(rawRecruitList.size()).build();
    }
}
