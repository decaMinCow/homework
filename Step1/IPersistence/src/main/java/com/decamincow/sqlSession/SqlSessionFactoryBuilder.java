package com.decamincow.sqlSession;

import com.decamincow.config.XMLConfigBuilder;
import com.decamincow.po.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * @ClassName SqlSessionFactoryBuilder
 * @Description TODO
 * @Author decamincow
 * @Date 25/02/2020 4:50 PM
 * @Version 1.0
 **/
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream in) throws PropertyVetoException, DocumentException {
        /**
         * 使用 dom4j 解析配置文件，内容封装到 Configuration 中
         */
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(in);

        /**
         * 创建 sqlSession
         */

        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);

        return defaultSqlSessionFactory;
    }
}

