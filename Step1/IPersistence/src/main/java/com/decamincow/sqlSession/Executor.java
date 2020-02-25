package com.decamincow.sqlSession;

import com.decamincow.po.Configuration;
import com.decamincow.po.MappedStatement;

import java.sql.SQLException;
import java.util.List;

public interface Executor {

    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws SQLException, Exception;

    public int update(Configuration configuration, MappedStatement mappedStatement, Object... params) throws SQLException, Exception;
}
