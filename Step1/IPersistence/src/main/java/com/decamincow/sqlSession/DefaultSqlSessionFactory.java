package com.decamincow.sqlSession;

import com.decamincow.po.Configuration;

/**
 * @ClassName DefaultSqlSessionFactory
 * @Description TODO
 * @Author decamincow
 * @Date 25/02/2020 5:47 PM
 * @Version 1.0
 **/
public class DefaultSqlSessionFactory implements SqlSessionFactory{

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration){
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSesson() {
        return new DefaultSqlSession(configuration);
    }
}
