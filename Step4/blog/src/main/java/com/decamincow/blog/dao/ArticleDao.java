package com.decamincow.blog.dao;


import com.decamincow.blog.model.po.Article;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @ClassName Article
 * @Description TODO
 * @Author decamincow
 * @Date 26/03/2020 12:59 PM
 * @Version 1.0
 **/
public interface ArticleDao extends PagingAndSortingRepository<Article, Long> {


}
