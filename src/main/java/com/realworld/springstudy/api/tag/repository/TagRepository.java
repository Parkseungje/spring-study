package com.realworld.springstudy.api.tag.repository;

import com.realworld.springstudy.api.article.entity.Article;
import com.realworld.springstudy.api.tag.entity.ArticleTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<ArticleTag, Long> {

    List<ArticleTag> findByArticle(Article article);
}
