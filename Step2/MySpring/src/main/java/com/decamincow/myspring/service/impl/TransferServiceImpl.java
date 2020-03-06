package com.decamincow.myspring.service.impl;

import com.decamincow.myspring.annotation.MyAutowired;
import com.decamincow.myspring.annotation.MyService;
import com.decamincow.myspring.annotation.MyTransactional;
import com.decamincow.myspring.dao.AccountDao;
import com.decamincow.myspring.po.Account;
import com.decamincow.myspring.service.TransferService;
import com.decamincow.myspring.utils.TransactionManager;
import lombok.Data;

/**
 * @author 应癫
 */
@Data
@MyService
//@MyService("123")
public class TransferServiceImpl implements TransferService {

    @MyAutowired
    private AccountDao accountDao;

    @MyAutowired
    private TransactionManager transactionManager;

    @MyTransactional
    @Override
    public void transfer(int fromCardNo, int toCardNo, int money) throws Exception {

        Account from = accountDao.searchAccountByCardNo(fromCardNo);
        Account to = accountDao.searchAccountByCardNo(toCardNo);

        from.setMoney(from.getMoney()-money);
        to.setMoney(to.getMoney()+money);

        accountDao.updateAccountByCardNo(to);
        int c = 1/0;
        accountDao.updateAccountByCardNo(from);

    }
}
