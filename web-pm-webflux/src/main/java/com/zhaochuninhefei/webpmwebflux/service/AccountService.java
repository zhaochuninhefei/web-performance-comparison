package com.zhaochuninhefei.webpmwebflux.service;

import com.zhaochuninhefei.webpmwebflux.db.po.Accounts;
import com.zhaochuninhefei.webpmwebflux.db.repo.AccountsRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author zhaochun
 */
@Service
public class AccountService {
    AccountsRepository accountsRepository;

    public AccountService(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    public Flux<Accounts> findAll() {
        return accountsRepository.findAll();
    }

    public Mono<Accounts> findById(long id) {
        return accountsRepository.findById(id);
    }

    public Mono<Long> save(Accounts account) {
        return accountsRepository.save(account).map(Accounts::getId);
    }
}
