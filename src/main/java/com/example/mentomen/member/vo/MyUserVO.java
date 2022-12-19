package com.example.mentomen.member.vo;

import java.util.List;

import com.example.mentomen.article.vo.ArticleListVO;
import com.example.mentomen.article.vo.ArticleVO;
import com.example.mentomen.comment.vo.CommentVO;
import com.example.mentomen.member.dto.UserDto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MyUserVO {
    private UserDto user;
    private ArticleListVO articles;
    private List<CommentVO> comments;
    private List<ArticleVO> scraps;
}
