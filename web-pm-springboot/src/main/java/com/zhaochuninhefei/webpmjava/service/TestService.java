package com.zhaochuninhefei.webpmjava.service;

import com.zhaochuninhefei.webpmjava.db.dao.AccountsMapper;
import com.zhaochuninhefei.webpmjava.db.dao.PostMapper;
import com.zhaochuninhefei.webpmjava.db.po.Accounts;
import com.zhaochuninhefei.webpmjava.db.po.Post;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhaochun
 */
@Service
public class TestService {
    private AccountsMapper accountsMapper;

    private PostMapper postMapper;

    public TestService(AccountsMapper accountsMapper, PostMapper postMapper) {
        this.accountsMapper = accountsMapper;
        this.postMapper = postMapper;
    }

    @Transactional
    public String testSql() {
        System.out.println("accountsMapper.selectByPrimaryKey first time");
        Accounts account1 = accountsMapper.selectByPrimaryKey(1L);
        System.out.println("postMapper.selectByPrimaryKey first time");
        Post post1 = postMapper.selectByPrimaryKey(1L);

        System.out.println("accountsMapper.selectByPrimaryKey second time");
        Accounts account2 = accountsMapper.selectByPrimaryKey(1L);
        System.out.println("postMapper.selectByPrimaryKey second time");
        Post post2 = postMapper.selectByPrimaryKey(2L);

//        System.out.println("postMapper.updateByPrimaryKey");
//        post1.setContent(post1.getContent()+".");
//        postMapper.updateByPrimaryKey(post1);

        System.out.println("accountsMapper.selectByPrimaryKey third time");
        Accounts account3 = accountsMapper.selectByPrimaryKey(1L);
        System.out.println("postMapper.selectByPrimaryKey third time");
        Post post3 = postMapper.selectByPrimaryKey(1L);

        // 比较 account1 与 account2，post1 与 post2
        var strAccount1 = account1.toString();
        var strAccount2 = account2.toString();
        var strAccount3 = account3.toString();
        var strPost1 = post1.toString();
        var strPost2 = post2.toString();
        var strPost3 = post3.toString();
        System.out.println(strAccount1.equals(strAccount2));
        System.out.println(strPost1.equals(strPost2));

        // 用换行符连接 strAccount1，strAccount2，strPost1，strPost2，然后返回
        return strAccount1 + "\n" + strAccount2 + "\n" + strPost1 + "\n" + strPost2 + "\n" + strAccount3 + "\n" + strPost3;
    }
}
