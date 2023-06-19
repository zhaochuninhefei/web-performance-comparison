package com.zhaochuninhefei.webpmwebflux.db.repo;

import com.zhaochuninhefei.webpmwebflux.db.po.Accounts;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface AccountsRepository extends R2dbcRepository<Accounts, Long> {
//    @NotNull
//    Flux<Accounts> findAll();
//
//    @NotNull
//    Mono<Accounts> findById(@NotNull Long id);
}
