package com.realworld.springstudy.api.article.service;

import com.realworld.springstudy.api.article.dto.ArticleRequest;

import com.realworld.springstudy.api.article.dto.ArticleResponse;
import com.realworld.springstudy.api.article.dto.ArticleUpdateRequest;
import com.realworld.springstudy.api.article.dto.CommentRequest;
import com.realworld.springstudy.api.article.entity.Article;
import com.realworld.springstudy.api.article.entity.Article.ArticleBuilder;
import com.realworld.springstudy.api.article.entity.Comment;
import com.realworld.springstudy.api.article.entity.Favorite;
import com.realworld.springstudy.api.article.repository.ArticleRepository;
import com.realworld.springstudy.api.article.repository.CommentRepository;
import com.realworld.springstudy.api.article.repository.FavoriteRepository;
import com.realworld.springstudy.api.tag.service.TagService;
import com.realworld.springstudy.api.user.dto.Author;
import com.realworld.springstudy.api.user.entity.User;
import com.realworld.springstudy.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final FavoriteRepository favoriteRepository;
    private final UserService userService;
    private final TagService tagService;

    public void addArticles(ArticleRequest articleRequest) {
        ArticleBuilder builder = Article.builder();

        builder.description(articleRequest.getDescription());
        builder.title(articleRequest.getTitle());
        builder.body(articleRequest.getBody());
        builder.author(userService.getCurrentUser());

        builder.slug(this.createSlug(articleRequest.getTitle()));

        Article build = builder.build(); // use builder entity create
        tagService.addTagList(build, articleRequest.getTagList());
        articleRepository.save(build); // jpa extends auto save

    }

    private String createSlug(String title){
        return title.toLowerCase().replaceAll(" ", "-");
    }


    public List<ArticleResponse> getArticleListAll() {
        List<Article> articleList = articleRepository.findAll();
        List<ArticleResponse> list = new ArrayList<>();
        for (Article article : articleList) {
            ArticleResponse articleResponse = createArticleResponse(article);
            list.add(articleResponse);
        }
        return list;
    }

    private ArticleResponse createArticleResponse(Article article) {
        ArticleResponse articleResponse = new ArticleResponse();
        articleResponse.setBody(article.getBody());
        articleResponse.setDescription(article.getDescription());
        articleResponse.setCreatedAt(article.getCreatedTime());
        articleResponse.setUpdatedAt(article.getUpdatedTime());
        articleResponse.setFavoritesCount(favoriteRepository.countByArticleId(article.getId()).intValue());
        articleResponse.setSlug(article.getSlug());
        articleResponse.setTitle(article.getTitle());
        articleResponse.setFavorited(favoriteRepository.existsByUserAndArticle(article.getAuthor(), article));
        Author author = new Author();
        author.setBio(article.getAuthor().getBio());
        author.setImage(article.getAuthor().getImage());
        author.setUserName(article.getAuthor().getName());
        articleResponse.setAuthor(author);
        return articleResponse;
    }

    public ArticleResponse getArticleBySlug(String slug) {
        Article article = articleRepository.findBySlug(slug);
        return createArticleResponse(article);
    }

    @Transactional // 트랜잭션을 하겠다고 명시하는 어노테이션 (begin과 commit 처리를 하겠다는것)
    public void updateArticleBySlug(String slug, ArticleUpdateRequest body) {
        //jpa update
        //1. dirty check
        Article entity = articleRepository.findBySlug(slug);
        ArticleBuilder builder = Article.builder();

        builder.id(entity.getId());
        builder.slug(entity.getSlug());
        builder.description(entity.getDescription());

        builder.title(body.getTitle());

        articleRepository.save(builder.build());


        //2. 강제 update
    }

    @Transactional
    public void deleteArticlesBySlug(String slug) {

        Article entity = articleRepository.findBySlug(slug);

        articleRepository.deleteById(entity.getId());
    }

    public void addComments(String slug, CommentRequest commentRequest){

        // comment에 해당하는 article객체를 가져오는
        Article articleBySlug = articleRepository.findBySlug(slug);

        //Comment 만들기 (빌더로)
        Comment.CommentBuilder builder = Comment.builder();
        builder.article(articleBySlug);
        builder.author(userService.getCurrentUser());
        builder.body(commentRequest.getBody());

        commentRepository.save(builder.build());
    }

    public List<Comment> getCommentBySlug(String slug) {
        return commentRepository.findBySlug(slug);
    }

    @Transactional
    public void deleteCommentsBySlugAndId(String slug, Long commentId) {

        Article articleEntity = articleRepository.findBySlug(slug);

        Comment commentEntity = commentRepository.findByArticleAndId(articleEntity, commentId);

        commentRepository.deleteById(commentEntity.getId());
    }

    public void addFavorite(String slug){

        // 슬러그로 아티클 찾기
        Article article = articleRepository.findBySlug(slug);

        // 페이버릿 빌드하기
        Favorite.FavoriteBuilder builder = Favorite.builder();
        builder.user(userService.getCurrentUser());
        builder.article(article);

        favoriteRepository.save(builder.build());
    }

    @Transactional
    public void deleteFavoriteBySlug(String slug) {

        Favorite favoriteEntity = favoriteRepository.findBySlug(slug);

        favoriteRepository.deleteById(favoriteEntity.getId());
    }

}
