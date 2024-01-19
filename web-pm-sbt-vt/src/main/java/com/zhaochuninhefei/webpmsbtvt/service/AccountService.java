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

/**
 * @author zhaochun
 */
@Service
public class AccountService {
    @Autowired
    private AccountsMapper accountsMapper;

    @Autowired
    private JdbcClient jdbcClient;

    public List<Accounts> queryAllAccounts() {
        AccountsExample example = new AccountsExample();
        return accountsMapper.selectByExample(example);
    }

    public Accounts queryActByID(Long id) {
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
        return keyHolder.getKey().longValue();
    }
}
