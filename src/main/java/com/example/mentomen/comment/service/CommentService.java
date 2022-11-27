package com.example.mentomen.comment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mentomen.comment.dao.CommentDAO;
import com.example.mentomen.comment.mapper.CommentMapper;
import com.example.mentomen.comment.vo.CommentRetriveVO;
import com.example.mentomen.comment.vo.CommentVO;
import com.example.mentomen.member.dto.UserDto;
import com.example.mentomen.member.entity.UserEntity;
import com.example.mentomen.member.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentService {

  @Autowired
  private CommentMapper commentMapper;

  @Autowired
  private UserRepository userRepository;

  @Transactional(readOnly = true)
  public List<CommentVO> comments(
      Integer articleId, Long userId) {
    List<CommentVO> comments = new ArrayList<>();
    List<CommentDAO> rawCommentDAOs = commentMapper.getCommentList(articleId, userId);
    for (CommentDAO rawCommentDAO : rawCommentDAOs) {
      if (rawCommentDAO.getParentId() == null) {
        CommentVO parentComment = getParentComment(rawCommentDAO);
        comments.add(parentComment);
      }
    }
    return comments;
  }

  public Integer update(Integer commentId, Integer childId, CommentRetriveVO comment) {
    return commentMapper.updateComment(commentId, comment.getContents());
  }

  public Integer save(Integer articleId, Integer parentId, CommentRetriveVO comment) {
    // TODO from RetriveVO to VO
    Long createrId = 2L;
    Integer res = commentMapper.saveComment(createrId, articleId, parentId,
        comment.getContents());
    return res;
  }

  public Integer delete(Integer id) {
    return commentMapper.deleteComment(id);
  }

  private CommentVO getParentComment(CommentDAO rawCommentDAO) {
    List<CommentVO> childCommentList = new ArrayList<>();
    if (rawCommentDAO.getParentId() == null) {
      List<CommentDAO> rawChildCommentList = commentMapper.getCommentChildList(rawCommentDAO.getCommentId());
      if (rawChildCommentList.size() > 0) {
        for (CommentDAO rawChildComment : rawChildCommentList) {
          childCommentList.add(commonCommentVOBuilder(rawChildComment, null));
        }
      }
    }
    return commonCommentVOBuilder(rawCommentDAO, childCommentList);
  }

  private CommentVO commonCommentVOBuilder(CommentDAO commentDao, List<CommentVO> childComment) {
    UserEntity rawUser = userRepository.findById(commentDao.getCreatorId());
    UserDto creater = UserDto.builder().name(rawUser.getUsername()).email(rawUser.getEmail())
        .picture(rawUser.getPicture()).build();

    return CommentVO.builder().commentId(commentDao.getCommentId()).creater(creater)
        .createdAt(commentDao.getCreatedAt()).modifiedAt(commentDao.getModifiedAt())
        .contents(commentDao.getContents()).childs(childComment)
        .build();
  }

  public Integer getCommentCntByArticleId(Integer articleId) {
    return commentMapper.getCommentCnt(articleId);
  }
}
