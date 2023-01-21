package com.realworld.springstudy.api.user.dto;

import lombok.Data;

@Data
public class Author {

    private String userName;
    private String bio;
    private String image;
    private boolean following;
}
