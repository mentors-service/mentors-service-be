package com.example.mentomen.article.entity;

import com.example.mentomen.member.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Article {

    @Id
    @Column(name="article_id")
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동 생성 전략
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private UserEntity user;

    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        if (title != null)
            this.title = title;
        if (content != null)
            this.content = content;
    }
}
