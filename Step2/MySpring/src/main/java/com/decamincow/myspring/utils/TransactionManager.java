package com.decamincow.myspring.utils;

import com.decamincow.myspring.annotation.MyAutowired;
import com.decamincow.myspring.annotation.MyComponent;
import lombok.Data;

import java.sql.SQLException;

/**
 * @author 应癫
 *
 * 事务管理器类：负责手动事务的开启、提交、回滚
 */
@Data
@MyComponent
public class TransactionManager {

    @MyAutowired
    private ConnectionUtils connectionUtils;

    // 开启手动事务控制
    public void beginTransaction() throws SQLException {
        connectionUtils.getCurrentThreadConn().setAutoCommit(false);
    }


    // 提交事务
    public void commit() throws SQLException {
        connectionUtils.getCurrentThreadConn().commit();
    }


    // 回滚事务
    public void rollback() throws SQLException {
        connectionUtils.getCurrentThreadConn().rollback();
    }
}
