package com.zhaochuninhefei.webpmwebflux.controller;

import com.zhaochuninhefei.webpmwebflux.db.po.Accounts;
import com.zhaochuninhefei.webpmwebflux.dto.ResponseMsg;
import com.zhaochuninhefei.webpmwebflux.service.AccountService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
}
