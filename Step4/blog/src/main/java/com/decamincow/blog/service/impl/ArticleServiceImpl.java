package com.decamincow.blog.service.impl;

import com.decamincow.blog.dao.ArticleDao;
import com.decamincow.blog.model.po.Article;
import com.decamincow.blog.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @ClassName ArticleService
 * @Description TODO
 * @Author decamincow
 * @Date 26/03/2020 1:30 PM
 * @Version 1.0
 **/
@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    ArticleDao articleDao;

    @Override
    public Page<Article> getAllArticles(Integer pageNum, Integer pageSize){
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return articleDao.findAll(pageable);
    }
}
