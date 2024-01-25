package com.zhaochuninhefei.webpmsbtvt.service;

import com.zhaochuninhefei.webpmsbtvt.db.dao.AccountsMapper;
import com.zhaochuninhefei.webpmsbtvt.db.po.Accounts;
import com.zhaochuninhefei.webpmsbtvt.db.po.AccountsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

/**
 * @author zhaochun
 */
@SuppressWarnings({"unused", "CommentedOutCode"})
@Service
public class AccountService {
    @Autowired
    private AccountsMapper accountsMapper;

    @Autowired
    private JdbcClient jdbcClient;

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

    public Long addNewAccountByJdbcClient(Accounts account) {
        account.setActRegisterDate(LocalDateTime.now());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int update = jdbcClient.sql("insert into accounts (created_at, updated_at, deleted_at, act_name, act_pwd, act_nick_name, act_introduction, act_status, act_register_date) values (now(), now(), ?, ?, ?, ?, ?, ?, ?)")
                .params(account.getDeletedAt(), account.getActName(), account.getActPwd(), account.getActNickName(), account.getActIntroduction(), account.getActStatus(), account.getActRegisterDate())
                .update(keyHolder);
//        long id = Objects.requireNonNull(keyHolder.getKey()).longValue();
//        System.out.println("受影响行数: " + update + ", 新增帐户ID: " + id);
//        return id;
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
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
