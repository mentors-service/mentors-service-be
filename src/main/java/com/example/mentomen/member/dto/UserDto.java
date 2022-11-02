package com.example.mentomen.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserDto {
    private String email;
    private String name;
    private String picture;
}
