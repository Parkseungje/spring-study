package com.realworld.springstudy.api.tag.service;

import com.realworld.springstudy.api.article.entity.Article;
import com.realworld.springstudy.api.tag.entity.ArticleTag;
import com.realworld.springstudy.api.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public void addTagList(Article article, List<String> list) {
        List<ArticleTag> tagList = new ArrayList<>();
        for (String name :list) {
            ArticleTag.ArticleTagBuilder builder = ArticleTag.builder();
            builder.article(article);
            builder.tag(name);
            tagList.add(builder.build());
        }
        tagRepository.saveAll(tagList);
    }

    public List<String> getTagList(Article article) {
        return tagRepository.findByArticle(article).stream().map(ArticleTag::getTag).collect(Collectors.toList());
    }
}
