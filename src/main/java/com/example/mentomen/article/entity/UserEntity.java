package com.example.mentomen.article.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class UserEntity {

    @Id
    @Column(name="userEntity_id")
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동 생성 전략
    private Long id;

    @Column
    public String email;
    @Column
    public String name;
    @Column
    public String imageUrl;


    @Builder
    public UserEntity(String email, String name, String imageUrl,String password) {
        this.email = email;
        this.name = name;
        this.imageUrl = imageUrl;
    }

}
