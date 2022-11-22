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

@Service
public class CommentService {
  @Autowired
  private CommentMapper commentMapper;

  @Transactional(readOnly = true)
  public List<CommentVO> comments(
      Integer articleId) {
    List<CommentVO> comments = new ArrayList<>();
    List<CommentDAO> rawCommentDAOs = commentMapper.getCommentList(articleId);
    for (CommentDAO rawCommentDAO : rawCommentDAOs) {
      comments.add(commentVOBuilder(rawCommentDAO));
    }
    return comments;
  }

  public CommentVO update(Integer commentId, CommentRetriveVO comment) {
    return commentVOBuilder(
        commentMapper.updateComment(commentId, comment.getArticleId(), comment.getParentId(), comment.getContents()));
  }

  public Integer save(CommentRetriveVO comment) {
    // TODO from RetriveVO to VO

    return commentMapper.saveComment(comment.getArticleId(), comment.getParentId(), comment.getContents());
  }

  public Integer delete(Integer id) {
    return commentMapper.deleteComment(id);
  }

  // TODO 재귀함수 구현
  public CommentVO commentVOBuilder(CommentDAO rawComment) {
    List<CommentVO> childComments = new ArrayList<>();
    if (rawComment.getParentId() != null) {
      List<CommentDAO> rawChildCommentList = commentMapper.getCommentChildList(rawComment.getParentId());
      for (CommentDAO rawChildComment : rawChildCommentList) {
        childComments.add(childCommentVOBuilder(rawChildComment));
      }
    }
    // TODO Creater Mapper BY User ID
    return CommentVO.builder().commentId(rawComment.getCommentId()).creater(null)
        .createdAt(rawComment.getCreatedAt()).modifiedAt(rawComment.getModifiedAt()).childs(childComments).build();
  }

  public CommentVO childCommentVOBuilder(CommentDAO commentDao) {
    // TODO Creater Mapper BY User ID
    return CommentVO.builder().commentId(commentDao.getCommentId()).creater(null)
        .createdAt(commentDao.getCreatedAt()).modifiedAt(commentDao.getModifiedAt()).build();
  }

}
