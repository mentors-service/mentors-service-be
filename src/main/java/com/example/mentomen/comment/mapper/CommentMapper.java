package com.example.mentomen.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.mentomen.comment.vo.CommentVO;

@Mapper
public interface CommentMapper {
    List<CommentVO> getCommentList(@Param("article_id") Integer articleId);

    CommentVO updateComment(@Param("comment_id") Integer commentId);

    int saveComment();

    int deleteComment(@Param("comment_id") Integer commentId);
}
