package com.realworld.springstudy.api.login.controller;

import com.realworld.springstudy.api.login.dto.LoginDto;
import com.realworld.springstudy.api.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        return loginService.login(loginDto);
    }
}
