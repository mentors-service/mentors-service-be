package com.example.mentomen.member.dto;
import com.example.mentomen.member.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserDto {

    private String nickname;
    private String discription;

    public UserDto(UserEntity user) {

        this.nickname=user.getNickname();
        this.discription=user.getDiscription();
    }
}
