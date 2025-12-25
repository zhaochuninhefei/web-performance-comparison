package com.zhaochuninhefei.webpmjava.controller;

import com.zhaochuninhefei.webpmjava.db.po.Accounts;
import com.zhaochuninhefei.webpmjava.db.po.AmountByCtmLevel;
import com.zhaochuninhefei.webpmjava.dto.ResponseMsg;
import com.zhaochuninhefei.webpmjava.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

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
    public List<Accounts> queryAccountsByIdRange(@RequestParam(value = "size", required = false) int size) {
        return accountService.queryAccountsByIdRange(size);
    }

    @GetMapping("/queryAmountByCtmLevel")
    public List<AmountByCtmLevel> queryAmountByCtmLevel() {
        return accountService.queryAmountByCtmLevel();
    }

    @GetMapping("/countOrdersAll")
    public Long countOrdersAll() {
        return accountService.countOrdersAll();
    }
}
