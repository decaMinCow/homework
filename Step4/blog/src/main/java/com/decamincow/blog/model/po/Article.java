package com.decamincow.blog.model.po;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName Article
 * @Description TODO
 * @Author decamincow
 * @Date 26/03/2020 12:52 PM
 * @Version 1.0
 **/
@Entity
@Data
@Builder
@Table(name = "t_article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "created")
    private Date created;
    @Column(name = "modified")
    private Date modified;
    @Column(name = "categories")
    private String categories;
    @Column(name = "tags")
    private String tags;
    @Column(name = "allow_comment")
    private Integer tallow_commentags;
    @Column(name = "thumbnail")
    private String thumbnail;

    @Tolerate
    public Article() {
    }
}