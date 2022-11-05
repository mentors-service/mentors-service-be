package com.example.mentomen.article.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Scrap {
    @Id
    @Column(name = "scrap_id")
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동 생성 전략
    private Long id;

    @Column(name = "scrap_cnt")
    private Integer scrapCnt;
}
