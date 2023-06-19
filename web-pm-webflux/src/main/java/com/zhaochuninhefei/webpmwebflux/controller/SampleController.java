package com.zhaochuninhefei.webpmwebflux.controller;

import com.zhaochuninhefei.webpmwebflux.dto.Post;
import com.zhaochuninhefei.webpmwebflux.dto.ResponseMsg;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

/**
 * @author zhaochun
 */
@SuppressWarnings("HttpUrlsUsage")
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
        return webClient.get().uri("http://jsonplaceholder.typicode.com/posts").retrieve().bodyToFlux(Post.class);
    }

    @GetMapping("/jsonplaceholder/posts/userids")
    public Flux<Integer> userids() {
        WebClient webClient = WebClient.create();
        Flux<Post> posts = webClient.get().uri("http://jsonplaceholder.typicode.com/posts").retrieve().bodyToFlux(Post.class);
        // 对userid去重
        return posts.map(Post::getUserId).distinct();
    }

    @GetMapping("/jsonplaceholder/posts/userids2")
    public Mono<ResponseMsg<List<Integer>>> userids2() {
        // 打印当前线程ID
        System.out.println("userids2请求线程ID:" + Thread.currentThread().getId());
        WebClient webClient = WebClient.create();
        Flux<Post> posts = webClient.get().uri("http://jsonplaceholder.typicode.com/posts").retrieve().bodyToFlux(Post.class);
        return posts.map(Post::getUserId).distinct().collectList().map(list -> {
            System.out.println("userids2订阅者线程ID:" + Thread.currentThread().getId());
            ResponseMsg<List<Integer>> responseMsg = new ResponseMsg<>();
            responseMsg.setResCd("1");
            responseMsg.setResMsg("success");
            responseMsg.setData(list);
            return responseMsg;
        });
    }

    // 当http响应的Content-type是`text/event-stream`时,
    // Flux返回的是一个分批次的流,在浏览器访问`http://localhost:8080/sample/flux`即可看到分批返回的效果。
    //  Content-type不是`text/event-stream`时，Mono和Flux都是一次请求一次返回;
    //  但`text/event-stream`+Flux则是一次请求多次返回。
    @GetMapping(value = "/flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> flux() {
        System.out.println("flux请求线程ID:" + Thread.currentThread().getId());
        // `.publishOn(Schedulers.boundedElastic())`的作用是请求调度另外一个线程来执行数据生产
        return Flux.fromArray(new String[]{"a", "b", "c", "d"}).publishOn(Schedulers.boundedElastic()).map(s -> {
            System.out.println("flux生产数据线程ID:" + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "<letter:" + s + ">";
        });
    }
}
