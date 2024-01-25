package com.zhaochuninhefei.webpmjava.controller;

import com.zhaochuninhefei.webpmjava.db.po.Accounts;
import com.zhaochuninhefei.webpmjava.dto.ResponseMsg;
import com.zhaochuninhefei.webpmjava.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhaochun
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/list")
    public List<Accounts> listAllAccounts() {
        return accountService.queryAllAccounts();
    }

    @GetMapping("/query")
    public Accounts queryAccountByID() {
        return accountService.queryActByID();
    }

    @PostMapping("/add")
    public ResponseMsg addNewAccount(@RequestBody Accounts account) {
        Long newId = accountService.addNewAccount(account);
        ResponseMsg returnVal = new ResponseMsg();
        returnVal.setResCd("1");
        returnVal.setResMsg("新增帐户ID:" + newId);
        return returnVal;
    }

    @GetMapping("/queryByIdRange")
    public List<Accounts> queryAccountsByIdRange() {
        return accountService.queryAccountsByIdRange();
    }
}
