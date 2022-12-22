package com.zhaochuninhefei.webpmjava.service;

import com.zhaochuninhefei.webpmjava.db.dao.AccountsMapper;
import com.zhaochuninhefei.webpmjava.db.po.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zhaochun
 */
@Service
public class AccountService {
    @Autowired
    private AccountsMapper accountsMapper;

    public List<Accounts> queryAllAccounts() {
        return accountsMapper.selectByExample(null);
    }

    public Accounts queryActByID(Long id) {
        return accountsMapper.selectByPrimaryKey(id);
    }

    public Long addNewAccount(Accounts account) {
        account.setActRegisterDate(LocalDateTime.now());
        accountsMapper.insert(account);
        return account.getId();
    }
}
