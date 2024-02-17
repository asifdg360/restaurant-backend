package com.ar.rmsbackend.service;

import com.ar.rmsbackend.model.User;
import com.ar.rmsbackend.payload.request.UserRequestDto;
import com.ar.rmsbackend.payload.response.UserResponseDto;

import java.util.Set;

public interface UserService {
    User findByUserName(String username);

    User findByUserId(Long userId);
    UserResponseDto createUser(UserRequestDto requestDto);
    User save(UserRequestDto requestDto);

    Set<User> findAllUserByIdIn(Set<Long> userIds, Boolean isActive);
}
