package com.example.mentomen.article.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.example.mentomen.article.code.ArticleStatusCode;

@Entity
@NoArgsConstructor
@Getter
public class Article {

    @Id
    @Column(name = "article_id")
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동 생성 전략
    private Long id;

    @OneToOne
    @JoinColumn(name = "creater_id")
    // @OneToOne(mappedBy = "locker") 대상 Entity에 걸어주기
    private Long createrId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Column
    private String title;

    @Column
    private String place;

    @Column
    private String contents;

    @OneToOne
    @JoinColumn(name = "id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "id")
    private Scrap scrap;

    @OneToMany(mappedBy = "article")
    private List<Comment> comment = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ArticleStatusCode status;
}
