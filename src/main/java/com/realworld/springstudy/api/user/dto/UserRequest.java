package com.realworld.springstudy.api.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private String bio;
    private String image;
}

