package com.example.mentomen.member.repository;

import com.example.mentomen.member.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    // Jpa Naming 전략
    // SELECT * FROM user WHERE username = 1?
    UserEntity findByUsername(String username);
    // SELECT * FROM user WHERE username = 1? AND password = 2?
    // User findByUsernameAndPassword(String username, String password);
    UserEntity findByEmail(String email);
    UserEntity findById(Long id);


    // @Query(value = "select * from user", nativeQuery = true)
    // User find마음대로();
    // SELECT * FROM user WHERE provider = ?1 and providerId = ?2
    Optional<UserEntity> findByProviderAndProviderId(String provider, String providerId);
}
