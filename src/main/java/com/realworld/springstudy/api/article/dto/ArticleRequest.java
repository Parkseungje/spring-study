package com.realworld.springstudy.api.article.dto;


import lombok.Data;

import java.util.List;

@Data
public class ArticleRequest {
    private String title;
    private String description;
    private String body;
    private List<String> tagList;
}
