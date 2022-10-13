package com.example.mentomen.article.dto;

import com.example.mentomen.article.entity.Article;
import com.example.mentomen.article.entity.UserEntity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
public class UserDto {
    public Long id;
    public String email;
    public String name;
    public String imageUrl;


    public UserDto(UserEntity userEntity) {
        this.id=userEntity.getId();
        this.email=userEntity.getEmail();
        this.name=userEntity.getName();
        this.imageUrl=userEntity.getImageUrl();
    }

}
