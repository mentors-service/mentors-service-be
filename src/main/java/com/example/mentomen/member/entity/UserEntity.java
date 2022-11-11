package com.example.mentomen.member.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "user")
@NoArgsConstructor //파라미터가 없는 생성자를 생성한다.
public class UserEntity {

    @Id // primary key
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role; //ROLE_USER, ROLE_ADMIN

    private String provider; //google
    private String providerId;

    @NotBlank
    private String nickname;
    private String discription;

    @CreationTimestamp
    private Timestamp createDate;

    @Builder
    public UserEntity(String username, String password, String email, String role, String provider, String providerId, Timestamp createDate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
        this.createDate = createDate;
        this.nickname = username;
        this.discription = null;
    }

    public void update(String nickname, String discription) {
        this.nickname = nickname;
        if (discription != null)
            this.discription = discription;
    }

}
