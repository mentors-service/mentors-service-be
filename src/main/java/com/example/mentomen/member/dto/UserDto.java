package com.example.mentomen.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {
    private String email;
    private String name;
    private String picture;
}
