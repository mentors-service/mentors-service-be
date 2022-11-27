package com.example.mentomen.comment.vo;

import java.util.Date;
import java.util.List;

import com.example.mentomen.member.dto.UserDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentVO {
    private Integer commentId;
    private UserDto creater;
    private Date createdAt;
    private Date modifiedAt;
    private String contents;
    private List<CommentVO> childs;
}
