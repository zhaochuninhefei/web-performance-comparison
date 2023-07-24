package com.zhaochuninhefei.webpmwebflux.db.repo;

import com.zhaochuninhefei.webpmwebflux.db.po.Post;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface PostRepository extends R2dbcRepository<Post, Long> {
    @SuppressWarnings({"SqlNoDataSourceInspection", "SqlDialectInspection"})
    @Query("select * from post where act_id = :actId")
    Flux<Post> findByActId(Long actId);
}
