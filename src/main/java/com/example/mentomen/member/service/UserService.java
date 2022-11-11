package com.example.mentomen.member.service;

import com.example.mentomen.article.dto.ArticleRequestDto;
import com.example.mentomen.article.dto.ArticleResponseDto;
import com.example.mentomen.article.entity.Article;
import com.example.mentomen.article.repository.ArticleRepository;
import com.example.mentomen.member.dto.UserDto;
import com.example.mentomen.member.entity.UserEntity;
import com.example.mentomen.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDto findByEmail(String email) {

        UserEntity user = userRepository.findByEmail(email);

        return new UserDto(user);
    }

    public void update(String email, UserDto userDto) {

        UserEntity user = userRepository.findByEmail(email);

        user.update(userDto.getNickname(),userDto.getDiscription());
    }
}
