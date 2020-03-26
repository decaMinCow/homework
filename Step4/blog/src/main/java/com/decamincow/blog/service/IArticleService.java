package com.decamincow.blog.service;

import com.decamincow.blog.model.po.Article;
import org.springframework.data.domain.Page;

public interface IArticleService {

    Page<Article> getAllArticles(Integer pageNum, Integer pageSize);
}
