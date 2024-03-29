package com.realworld.springstudy.api.user.service;

import com.realworld.springstudy.api.user.entity.User;
import com.realworld.springstudy.api.user.entity.UserPrincipal;
import com.realworld.springstudy.api.user.repository.UserRepository;
import com.realworld.springstudy.global.commonException.CommonErrorCode;
import com.realworld.springstudy.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserPrincipalService {

    private final UserRepository userRepository;


    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email);
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        return userPrincipal;
    }

    public UserDetails loadUserByUsernameAndPassword(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password).orElseThrow(() -> {
            throw new UserNotFoundException(CommonErrorCode.NOT_FOUND, "not found");
        });
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        return userPrincipal;
    }
}
