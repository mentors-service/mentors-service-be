package com.example.mentomen.member.vo;

import com.example.mentomen.article.vo.ArticleListVO;
import com.example.mentomen.member.dto.UserDto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OtherUserVO {
    private UserDto user;
    private ArticleListVO articles;
}
