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
public class Member {
  // @Id
  // @Column(name = "member_id")
  // @GeneratedValue(strategy = GenerationType.AUTO) // 자동 생성 전략
  // private Long id;

  // @Column(name = "join_cnt")
  // private Integer joinCnt;

  // @Column(name = "total_cnt")
  // private Integer totalCnt;
}
