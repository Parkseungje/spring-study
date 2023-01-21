package com.realworld.springstudy.api.user.dto;

import lombok.Data;

@Data
public class Profile {
    private String username;
    private String bio;
    private String image;
    private boolean following;
}
