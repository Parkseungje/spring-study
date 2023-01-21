package com.realworld.springstudy.api.tag.repository;

import com.realworld.springstudy.api.tag.entity.ArticleTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<ArticleTag, Long> {
}
