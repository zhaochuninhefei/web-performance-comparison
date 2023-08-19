package com.zhaochuninhefei.webpmwebflux.controller;

import com.zhaochuninhefei.webpmwebflux.dto.Asset;
import com.zhaochuninhefei.webpmwebflux.dto.ResponseMsg;
import com.zhaochuninhefei.webpmwebflux.service.AssetService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author zhaochun
 */
@RestController
@RequestMapping("/asset")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("/list")
    public Mono<List<Asset>> queryAllAssets() {
        return assetService.queryAssetList();
    }

    @GetMapping("/query")
    public Mono<Asset> queryAssetById(@RequestParam String id) {
        return assetService.queryFromMap(id);
    }

    @PostMapping("/modify")
    public Mono<ResponseMsg<Asset>> modifyAsset(@RequestBody Asset asset) {
        return assetService.modifyAssetDto(asset);
    }
}
