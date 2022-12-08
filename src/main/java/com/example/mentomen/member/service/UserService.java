package com.example.mentomen.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mentomen.member.dto.UserDto;
import com.example.mentomen.member.entity.UserEntity;
import com.example.mentomen.member.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

        user.update(userDto.getNickname(), userDto.getDescription());
    }

    @Transactional(readOnly = true)
    public UserDto findById(Long userId) {

        UserEntity user = userRepository.findById(userId);

        return new UserDto(user);
    }
}
