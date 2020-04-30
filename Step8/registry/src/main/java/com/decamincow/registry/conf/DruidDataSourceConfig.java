package com.decamincow.registry.conf;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @ClassName DruidDataSourceConfig
 * @Description TODO
 * @Author decamincow
 * @Date 2020/4/30 3:36 PM
 * @Version 1.0
 **/

@Configuration
public class DruidDataSourceConfig {

//    String url = "jdbc:mysql://localhost:3306/test?useUnicode=yes&characterEncoding=utf8&autoReconnect=true&rewriteBatchedStatements=true&useSSL=false";
//    String username = "root";
//    String password = "root";
//    String driverClassName = "com.mysql.cj.jdbc.Driver";

    @Autowired
    MysqlConfig mysqlConfig;

    @Bean
    public DataSource getDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(mysqlConfig.getUrl());
        dataSource.setUsername(mysqlConfig.getUsername());
        dataSource.setPassword(mysqlConfig.getPassword());
        dataSource.setDriverClassName(mysqlConfig.getDriverClassName());
//        dataSource.setUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }
}
