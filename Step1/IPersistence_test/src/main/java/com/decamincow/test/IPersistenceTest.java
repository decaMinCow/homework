package com.decamincow.test;

import com.decamincow.dao.IUserDao;
import com.decamincow.io.Resources;
import com.decamincow.po.User;
import com.decamincow.sqlSession.SqlSession;
import com.decamincow.sqlSession.SqlSessionFactory;
import com.decamincow.sqlSession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @ClassName IPersistenceTest
 * @Description TODO
 * @Author decamincow
 * @Date 25/02/2020 4:00 PM
 * @Version 1.0
 **/
public class IPersistenceTest {

    @Test
    public void test() throws Exception {

        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSesson();

        User user = new User();
        user.setId(2L);
        user.setUsername("李五");

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        /**
         * 增
         */
//        userDao.create(user);

        /**
         * 改
         */
//        userDao.update(user);

        /**
         * 删
         */
        userDao.delete(user);

        /**
         * 查
         */
        List<User> all = userDao.findAll();
        for (User user1 : all) {
            System.out.println(user1);
        }
    }
}
