package com.example.mentomen.article.repository;

import com.example.mentomen.article.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {

    @Override
    ArrayList<Article> findAll();

}
