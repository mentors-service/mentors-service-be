package com.example.mentomen.article.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Comment {
  // @Id
  // @Column(name = "comment_id")
  // @GeneratedValue(strategy = GenerationType.AUTO) // 자동 생성 전략
  // private Long id;

  // @OneToOne
  // @JoinColumn(name = "creater_id")
  // // @OneToOne(mappedBy = "locker") 대상 Entity에 걸어주기
  // private Long createrId;

  // @ManyToOne
  // @JoinColumn(name = "article_id")
  // private Article article;

  // @Temporal(TemporalType.TIMESTAMP)
  // private Date createAt;

  // @Column
  // private String content;

  // @Column
  // private Long child;

}
