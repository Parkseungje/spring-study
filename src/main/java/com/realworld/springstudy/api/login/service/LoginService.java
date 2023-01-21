package com.realworld.springstudy.api.login.service;

import com.realworld.springstudy.api.login.dto.AccessTokenDto;
import com.realworld.springstudy.api.login.dto.LoginDto;
import com.realworld.springstudy.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JwtTokenProvider jwtTokenProvider;

    public String login(LoginDto loginDto) {
        return jwtTokenProvider.generateToken(loginDto.getEmail(), loginDto.getPassword());
    }
}
