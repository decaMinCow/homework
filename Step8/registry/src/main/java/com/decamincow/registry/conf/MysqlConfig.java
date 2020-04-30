package com.decamincow.registry.conf;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName MysqlConfig
 * @Description TODO
 * @Author decamincow
 * @Date 2020/4/30 3:52 PM
 * @Version 1.0
 **/
@Data
@Builder
@Component
public class MysqlConfig {

    @Tolerate
    public MysqlConfig() {
    }

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
}
