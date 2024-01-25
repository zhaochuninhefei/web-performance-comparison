package com.zhaochuninhefei.webpmjava.service;

import com.zhaochuninhefei.webpmjava.db.dao.AccountsMapper;
import com.zhaochuninhefei.webpmjava.db.po.Accounts;
import com.zhaochuninhefei.webpmjava.db.po.AccountsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

/**
 * @author zhaochun
 */
@Service
public class AccountService {
    @Autowired
    private AccountsMapper accountsMapper;

    RandomGenerator random = RandomGeneratorFactory.of("Random").create();

    public List<Accounts> queryAllAccounts() {
        AccountsExample example = new AccountsExample();
        return accountsMapper.selectByExample(example);
    }

    public Accounts queryActByID() {
        long id = random.nextInt(1, 1000);
        return accountsMapper.selectByPrimaryKey(id);
    }

    public Long addNewAccount(Accounts account) {
        account.setActRegisterDate(LocalDateTime.now());
        accountsMapper.insert(account);
        return account.getId();
    }

    public List<Accounts> queryAccountsByIdRange() {
        // 获取一个位于1到900之间的随机整数
        long startId = random.nextInt(1, 900);
        long endId = startId + 100;

        AccountsExample example = new AccountsExample();
        example.createCriteria().andIdBetween(startId, endId);
        return accountsMapper.selectByExample(example);
    }
}
