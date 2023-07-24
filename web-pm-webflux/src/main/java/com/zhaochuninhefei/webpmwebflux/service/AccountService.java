package com.zhaochuninhefei.webpmwebflux.service;

import com.zhaochuninhefei.webpmwebflux.db.po.Accounts;
import com.zhaochuninhefei.webpmwebflux.db.repo.AccountsRepository;
import com.zhaochuninhefei.webpmwebflux.db.repo.PostRepository;
import com.zhaochuninhefei.webpmwebflux.dto.AccountPosts;
import com.zhaochuninhefei.webpmwebflux.dto.ResponseMsg;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author zhaochun
 */
@Service
public class AccountService {
    AccountsRepository accountsRepository;

    PostRepository postRepository;

    public AccountService(AccountsRepository accountsRepository, PostRepository postRepository) {
        this.accountsRepository = accountsRepository;
        this.postRepository = postRepository;
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

    public Mono<ResponseMsg<List<AccountPosts>>> queryAllAccountPosts() {
        // 创建反应式流
        @SuppressWarnings("UnnecessaryLocalVariable")
        Mono<ResponseMsg<List<AccountPosts>>> result =
                // 请求查询Accounts全表，返回 Flux<Accounts>
                accountsRepository.findAll()
                        // 使用flatMap将每个account转换为Mono<AccountPosts>, 然后扁平化返回 Flux<AccountPosts>
                        // 内部有阻塞操作 postRepository.findByActId , 因此不能直接使用map方法。
                        .flatMap(account ->
                                // 请求对每个Account查询对应的Post，返回 Flux<Post>
                                postRepository.findByActId(account.getId())
                                        // 请求将每个Account对应的 Post 收集到一个List里, 返回 Mono<List<Post>>
                                        .collectList()
                                        // 请求为每个Account创建AccountPosts,返回 Mono<AccountPosts>
                                        .map(posts -> new AccountPosts(account, posts))
                        )
                        // 将Flux<AccountPosts>中的全部AccountPosts收集到list中,返回Mono<List<AccountPosts>>
                        .collectList()
                        // 将 List<AccountPosts> 封装到 ResponseMsg里
                        .map(list -> {
                            ResponseMsg<List<AccountPosts>> resp = new ResponseMsg<>();
                            resp.setResCd("1");
                            resp.setResMsg("请求成功");
                            resp.setData(list);
                            return resp;
                        });

        // 上面的代码在正常开始时写作:
//        return accountsRepository.findAll()
//                .flatMap(account -> postRepository.findByActId(account.getId()).collectList()
//                        .map(posts -> new AccountPosts(account, posts)))
//                .collectList()
//                .map(list -> {
//                    ResponseMsg<List<AccountPosts>> resp = new ResponseMsg<>();
//                    resp.setData(list);
//                    return resp;
//                });

        // 返回创建好的反应式流
        return result;
    }
}
