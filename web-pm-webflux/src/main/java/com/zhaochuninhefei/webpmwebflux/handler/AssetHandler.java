package com.zhaochuninhefei.webpmwebflux.handler;

import com.zhaochuninhefei.webpmwebflux.dto.Asset;
import com.zhaochuninhefei.webpmwebflux.dto.ResponseMsg;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaochun
 */
@Component
public class AssetHandler {
    private static final List<Asset> assets = new ArrayList<>();

    private static final Map<String, Asset> astMp = new HashMap<>();

    static {
        Asset ast1 = new Asset();
        ast1.setId(1);
        ast1.setName("asset001");
        ast1.setDesc("测试资产001");

        Asset ast2 = new Asset();
        ast2.setId(2);
        ast2.setName("asset002");
        ast2.setDesc("测试资产002");

        assets.add(ast1);
        assets.add(ast2);

        astMp.put("1", ast1);
        astMp.put("2", ast2);
    }

    private List<Asset> queryAssetList() {
        // 打印当前线程ID
        System.out.println("queryAssetList线程ID:" + Thread.currentThread().getId());
        return assets;
    }

    private Asset queryFromMap(String id) {
        // 打印当前线程ID
        System.out.println("queryFromMap线程ID:" + Thread.currentThread().getId());
        return astMp.get(id);
    }

    private ResponseMsg modifyAssetDto(Asset asset) {
        // 打印当前线程ID
        System.out.println("modifyAssetDto线程ID:" + Thread.currentThread().getId());
        System.out.println("修改目标: " + asset.toString());
        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.setResCd("1");
        responseMsg.setResMsg(asset.toString());
        return responseMsg;
    }

    public Mono<ServerResponse> queryAllAssets(ServerRequest serverRequest) {
        // 获取当前线程ID
        long curThreadId = Thread.currentThread().getId();
        // 打印当前线程ID
        System.out.println("queryAllAssets线程ID:" + curThreadId);
        // 1. 当前线程通过`Mono.just`创建一个Mono对象，这是一个发布者;
        //   然后马上执行`Mono.just`传入的方法，发布数据，但此时没有订阅者，数据缓存在Mono中。
        // 2. 之后通过`ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body()`定义了一个Processor(订阅者+发布者)的实现逻辑,
        //   这段逻辑相当于为一个订阅者实现了部分onNext中的实现，并继续返回Mono对象(Mono的泛型发生了变化)。
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(queryAssetList()), Asset.class);
        // 3. 该线程返回Mono对象给外层代码，外层通过RouterFunctions.route方法绑定请求uri与mono之间的对应关系，并为该mono完善订阅者处理逻辑并注册订阅者。
        // 4. 之后缓存在mono的数据会被发送给订阅者，订阅者通过webflux的线程调度启用的某个线程执行订阅逻辑，包括将数据写入http响应。
    }

    public Mono<ServerResponse> queryAssetById(ServerRequest serverRequest) {
        // 获取当前线程ID
        long curThreadId = Thread.currentThread().getId();
        // 打印当前线程ID
        System.out.println("queryAssetById线程ID:" + curThreadId);
        // 从serverRequest 获取 URL参数 id
        String id = serverRequest.queryParam("id").orElse("1");
        // 1. 当前线程通过`Mono.fromSupplier`创建一个Mono对象，这是一个发布者;
        //   但与`Mono.just`不同的是，这里传入的`queryFromMap(id)`不会马上执行，而是在当前线程返回mono对象给外层执行完整订阅之后才会执行。
        // 2. 之后通过`ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body()`定义了一个Processor(订阅者+发布者)的实现逻辑。
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(Mono.fromSupplier(() -> queryFromMap(id)), Asset.class);
        // 3. 该线程返回Mono对象给外层代码，外层通过RouterFunctions.route方法绑定请求uri与mono之间的对应关系，并为该mono完善订阅者处理逻辑并注册订阅者。
        // 4. 该线程执行`Mono.fromSupplier`传入的`queryFromMap(id)`方法，发布数据。
        // 5. 数据会被发送给订阅者，订阅者通过webflux的线程调度启用的某个线程执行订阅逻辑，包括将数据写入http响应。
        // PS:如果想让`queryFromMap(id)`不在queryAssetById线程中执行，可以用`Mono.fromSupplier(xxx).subscribeOn(Schedulers.boundedElastic())`的方式，让webflux选取另一个线程来执行数据发布。
        //   但其实没有必要,因为按照反应式编程的要求，当前的queryAssetById线程不应该有阻塞操作，所以没必要换线程执行数据发布。
    }

    public Mono<ServerResponse> modifyAsset(ServerRequest serverRequest) {
        // 获取当前线程ID
        long curThreadId = Thread.currentThread().getId();
        // 打印当前线程ID
        System.out.println("modifyAsset线程ID:" + curThreadId);
        // 1. 直接创建一个Mono生产者，并定义生产数据的处理逻辑(此时不会马上将请求体数据转换为Asset对象，而是等到后续订阅者注册之后再执行)
        Mono<Asset> assetMono = serverRequest.bodyToMono(Asset.class);
        // 2. 通过`assetMono.flatMap`为mono定义一个Processor(订阅者+发布者)的实现逻辑，并返回新的mono
        return assetMono.flatMap(asset -> {
            ResponseMsg responseMsg = modifyAssetDto(asset);
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(responseMsg);
        });
        // 3. 该线程返回Mono对象给外层代码，外层通过RouterFunctions.route方法绑定请求uri与mono之间的对应关系，并为该mono完善订阅者处理逻辑并注册订阅者。
        // 4. 该线程执行`serverRequest.bodyToMono`定义的转换逻辑，将请求体数据转为Asset对象，即发布数据。
        // 5. 数据会被发送给订阅者，订阅者通过webflux的线程调度启用的某个线程执行订阅逻辑，包括`assetMono.flatMap`中定义的逻辑如`modifyAssetDto()`，
        //    以及`ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(responseMsg)`。
    }
}
