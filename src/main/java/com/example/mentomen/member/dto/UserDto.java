package com.example.mentomen.member.dto;

import com.example.mentomen.member.entity.UserEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserDto {
    private Long id;
    private String nickname;
    private String description;

    public UserDto(UserEntity user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.description = user.getDescription();
    }
}
