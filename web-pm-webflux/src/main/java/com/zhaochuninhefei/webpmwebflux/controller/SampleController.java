package com.zhaochuninhefei.webpmwebflux.controller;

import com.zhaochuninhefei.webpmwebflux.dto.Post;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author zhaochun
 */
@RestController
@RequestMapping("/sample")
public class SampleController {
    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("Hello, SampleController in WebFlux.");
    }

    // 访问互联网站点:`http://jsonplaceholder.typicode.com/posts`
    //  `http://jsonplaceholder.typicode.com/posts`是一个用于测试和原型制作的免费假REST API。
    //  您可以使用它来获取一些虚拟的数据，例如文章、评论、相册、照片、待办事项和用户。
    //  您还可以使用不同的HTTP方法来创建、更新或删除资源。这个网址是由JSON Server和LowDB提供支持的。
    @GetMapping("/jsonplaceholder/posts")
    public Flux<Post> posts() {
        WebClient webClient = WebClient.create();
        //noinspection HttpUrlsUsage
        return webClient.get().uri("http://jsonplaceholder.typicode.com/posts").retrieve().bodyToFlux(Post.class);
    }
}
