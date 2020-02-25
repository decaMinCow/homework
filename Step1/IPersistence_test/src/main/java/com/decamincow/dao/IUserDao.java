package com.decamincow.dao;


import com.decamincow.po.User;

import java.util.List;

public interface IUserDao {

    public List<User> findAll() throws Exception;

    public User findByCondition(User user) throws Exception;

    public int create(User user) throws Exception;

    public int delete(User user) throws Exception;

    public int update(User user) throws Exception;

}
