package com.ar.rmsbackend.service.Impl;

import com.ar.rmsbackend.common.constant.ErrorId;
import com.ar.rmsbackend.common.exception.RmsServerException;
import com.ar.rmsbackend.config.authentication.service.CustomUserDetails;
import com.ar.rmsbackend.mapper.UserMapper;
import com.ar.rmsbackend.model.User;
import com.ar.rmsbackend.payload.request.UserRequestDto;
import com.ar.rmsbackend.payload.response.UserResponseDto;
import com.ar.rmsbackend.repository.UserRepository;
import com.ar.rmsbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public User findByUserName(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(() -> RmsServerException.notFound(ErrorId.NOT_FOUND));
    }

    @Override
    public User findByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> RmsServerException.notFound(""));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            return CustomUserDetails.build(user.get());
        } else {
            throw new UsernameNotFoundException("User Not Found" + username);
        }
    }

    @Override
    @Transactional
    public UserResponseDto createUser(UserRequestDto requestDto) {
        User user = userMapper.convertToEntity(requestDto);
        return userMapper.convertToResponse(userRepository.save(user));
    }


    @Override
    @Transactional
    public User save(UserRequestDto requestDto) {
        User user = userMapper.convertToEntity(requestDto);
        return userRepository.save(user);
    }

    @Override
    public Set<User> findAllUserByIdIn(Set<Long> userIds, Boolean isActive) {
        return userRepository.findAllByIdInAndIsActive(userIds, isActive);
    }
}
