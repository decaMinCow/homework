package com.decamincow.sqlSession;

import java.util.List;

public interface SqlSession {

    public <E>List selectList(String statementid, Object... params) throws Exception;

    public <T> T selectOne(String statementid, Object... params) throws Exception;

    public <T> T getMapper(Class<?> mapperClass);

    public int update(String statementid, Object... params) throws Exception;

}
