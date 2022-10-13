package com.example.mentomen.article.dto;

import com.example.mentomen.article.entity.Article;
import com.example.mentomen.article.entity.UserEntity;
import lombok.*;

@Getter
@NoArgsConstructor
//@Setter
public class SocialDto {
    //public Long id;
    public String email;
    public String name;
    public String imageUrl;

    @Builder
    public SocialDto(String email, String name, String imageUrl) {
        this.email = email;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public UserEntity toEntity() {
        return UserEntity.builder()
                .email(email)
                .name(name)
                .imageUrl(imageUrl)
                .build();
    }


}
