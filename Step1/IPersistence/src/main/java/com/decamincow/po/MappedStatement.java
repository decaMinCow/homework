package com.decamincow.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName MappedStatement
 * @Description TODO
 * @Author decamincow
 * @Date 25/02/2020 4:21 PM
 * @Version 1.0
 **/
@Data
public class MappedStatement implements Serializable {

    /**
     * id 标识
     */
    private String id;

    /**
     * 返回值类型
     */
    private String resultType;

    /**
     * 参数类型
     */
    private String paramterType;

    /**
     * sql 语句
     */
    private String sql;
}
