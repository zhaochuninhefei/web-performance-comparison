package com.zhaochuninhefei.webpmwebflux.controller;

import com.zhaochuninhefei.webpmwebflux.db.po.Accounts;
import com.zhaochuninhefei.webpmwebflux.dto.AccountPosts;
import com.zhaochuninhefei.webpmwebflux.dto.ResponseMsg;
import com.zhaochuninhefei.webpmwebflux.service.AccountService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author zhaochun
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/list")
    public Flux<Accounts> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/query")
    public Mono<Accounts> findById(@RequestParam long id) {
        return accountService.findById(id);
    }

    @PostMapping("/add")
    public Mono<ResponseMsg<Long>> save(@RequestBody Accounts account) {
        return accountService.save(account).map(newId -> {
            ResponseMsg<Long> responseMsg = new ResponseMsg<>();
            responseMsg.setResMsg("新增帐户ID:" + newId);
            responseMsg.setResCd("1");
            return responseMsg;
        });
    }

    // 调用该API前，在对应数据库执行:
    //    INSERT INTO db_web_pm.accounts (created_at,updated_at,act_name,act_pwd,act_nick_name,act_introduction,act_status)
    //    VALUES
    //        ('2023-07-24 00:00:00','2023-07-24 00:00:00','随便1','11111','随便1','随便1',1),
    //        ('2023-07-24 00:00:00','2023-07-24 00:00:00','随便2','11111','随便2','随便2',1),
    //        ('2023-07-24 00:00:00','2023-07-24 00:00:00','随便3','11111','随便3','随便3',1),
    //        ('2023-07-24 00:00:00','2023-07-24 00:00:00','随便4','11111','随便4','随便4',1),
    //        ('2023-07-24 00:00:00','2023-07-24 00:00:00','随便5','11111','随便5','随便5',1)
    //    ;
    //
    //    INSERT INTO db_web_pm.post (act_id,title,content)
    //    VALUES
    //        (1,'帖子1_1','帖子1_1'),
    //        (1,'帖子1_2','帖子1_2'),
    //        (2,'帖子2_1','帖子2_1'),
    //        (5,'帖子5_1','帖子5_1'),
    //        (5,'帖子5_2','帖子5_2')
    //    ;
    @GetMapping("/post/all")
    public Mono<ResponseMsg<List<AccountPosts>>> queryAllAccountPosts() {
        return accountService.queryAllAccountPosts();
    }
}
