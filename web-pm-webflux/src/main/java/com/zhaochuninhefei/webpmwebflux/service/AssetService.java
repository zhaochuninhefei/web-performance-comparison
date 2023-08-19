package com.zhaochuninhefei.webpmwebflux.service;

import com.zhaochuninhefei.webpmwebflux.dto.Asset;
import com.zhaochuninhefei.webpmwebflux.dto.ResponseMsg;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaochun
 */
@Service
public class AssetService {

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

    public Mono<List<Asset>> queryAssetList() {
        return Mono.just(assets);
    }

    public Mono<Asset> queryFromMap(String id) {
        return Mono.just(astMp.get(id));
    }

    public Mono<ResponseMsg<Asset>> modifyAssetDto(Asset asset) {
        return Mono.fromSupplier(() -> {
            System.out.println("修改目标: " + asset.toString());
            ResponseMsg<Asset> responseMsg = new ResponseMsg<>();
            responseMsg.setResCd("1");
            responseMsg.setResMsg(asset.toString());
            return responseMsg;
        });
    }

}
