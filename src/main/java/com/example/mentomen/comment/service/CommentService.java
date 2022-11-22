package com.example.mentomen.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mentomen.comment.mapper.CommentMapper;
import com.example.mentomen.comment.vo.CommentVO;

@Service
public class CommentService {
  @Autowired
  private CommentMapper commentMapper;

  @Transactional(readOnly = true)
  public List<CommentVO> comments(
      Integer articleId) {
    return commentMapper.getCommentList(articleId);
  }

  public CommentVO update(Integer id) {
    return commentMapper.updateComment(id);
  }

  public Integer save(CommentVO commentVO) {
    return commentMapper.saveComment();
  }

  public Integer delete(Integer id) {
    return commentMapper.deleteComment(id);
  }

  public CommentVO commentBuilder() {
    return CommentVO.builder().build();
  }
}
