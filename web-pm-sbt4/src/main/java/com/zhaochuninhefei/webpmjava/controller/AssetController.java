package com.zhaochuninhefei.webpmjava.controller;

import com.zhaochuninhefei.webpmjava.dto.Asset;
import com.zhaochuninhefei.webpmjava.dto.ResponseMsg;
import com.zhaochuninhefei.webpmjava.service.AssetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/asset")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("/list")
    public List<Asset> queryAllAssets() {
        return assetService.queryAllAssets();
    }

    @GetMapping("/query")
    public Asset queryAssetById(@RequestParam String id) {
        return assetService.queryAssetById(id);
    }

    @PostMapping("/modify")
    public ResponseMsg modifyAsset(@RequestBody Asset asset) {
        return assetService.modifyAsset(asset);
    }
}
