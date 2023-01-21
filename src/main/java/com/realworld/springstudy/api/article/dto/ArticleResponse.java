package com.realworld.springstudy.api.article.dto;

import com.realworld.springstudy.api.user.dto.Author;
import com.realworld.springstudy.api.user.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class ArticleResponse {

    private String slug;
    private String title;
    private String description;
    private String body;
    private Instant createdAt;
    private Instant updatedAt;

    private boolean favorited;
    private int favoritesCount;

    private List<String> tagList;

    private Author author;
}
