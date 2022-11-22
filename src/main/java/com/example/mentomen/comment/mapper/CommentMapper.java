package com.example.mentomen.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.mentomen.comment.dao.CommentDAO;

@Mapper
public interface CommentMapper {
        List<CommentDAO> getCommentList(@Param("article_id") Integer articleId);

        List<CommentDAO> getCommentChildList(@Param("parent_id") Integer parentId);

        CommentDAO updateComment(@Param("comment_id") Integer commentId, @Param("article_id") Integer articleId,
                        @Param("parent_id") Integer parentId,
                        @Param("contents") String contents);

        int saveComment(@Param("article_id") Integer articleId, @Param("parent_id") Integer parentId,
                        @Param("contents") String contents);

        int deleteComment(@Param("comment_id") Integer commentId);
}
