package com.realworld.springstudy.api.article.controller;

import com.realworld.springstudy.api.article.dto.ArticleRequest;
import com.realworld.springstudy.api.article.dto.ArticleResponse;
import com.realworld.springstudy.api.article.dto.ArticleUpdateRequest;
import com.realworld.springstudy.api.article.dto.CommentRequest;
import com.realworld.springstudy.api.article.entity.Comment;
import com.realworld.springstudy.api.article.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/articles")
    public void addArticles(@RequestBody ArticleRequest articleRequest){
        articleService.addArticles(articleRequest);

    }

    @GetMapping("/articles")
    public List<ArticleResponse> getArticles() {

        return articleService.getArticleListAll();
    }

    @GetMapping("/articles/{slug}")
    public ArticleResponse getArticleBySlug(@PathVariable String slug){

        return articleService.getArticleBySlug(slug);
    }

    @PutMapping("/articles/{slug}")
    public void updateArticleBySlug(@PathVariable String slug, @RequestBody ArticleUpdateRequest body) {
        articleService.updateArticleBySlug(slug, body);
    }

    @DeleteMapping("/articles/{slug}")
    public void deleteArticlesBySlug(@PathVariable String slug){
        articleService.deleteArticlesBySlug(slug);
    }

    @PostMapping("/articles/{slug}/comments")
    public void addComments(@PathVariable String slug, @RequestBody CommentRequest commentRequest) {
        articleService.addComments(slug,commentRequest);
    }

    @GetMapping("/articles/{slug}/comments")
    public List<Comment> getCommentsBySlug(@PathVariable String slug) {

        return articleService.getCommentBySlug(slug);

    }

    @DeleteMapping("/articles/{slug}/comments/{commentId}")
    public void deleteCommentsBySlugAndId(@PathVariable String slug, @PathVariable Long commentId){
        articleService.deleteCommentsBySlugAndId(slug,commentId);
    }

    @PostMapping("/articles/{slug}/favorite")
    public void addFavorite(@PathVariable String slug) {
        articleService.addFavorite(slug);
    }

    @DeleteMapping("/articles/{slug}/favorite")
    public void deleteFavoriteBySlug(@PathVariable String slug){
        articleService.deleteFavoriteBySlug(slug);
    }
}
