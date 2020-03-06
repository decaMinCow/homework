package com.decamincow.myspring.dao;

import com.decamincow.myspring.po.Account;

/**
 * @ClassName AccountDao
 * @Description TODO
 * @Author decamincow
 * @Date 06/03/2020 11:53 AM
 * @Version 1.0
 **/

public interface AccountDao {

    int updateAccountByCardNo(Account account) throws Exception;

    Account searchAccountByCardNo(int cardNo) throws Exception;

}