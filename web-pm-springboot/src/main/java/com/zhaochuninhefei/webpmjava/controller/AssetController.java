package com.zhaochuninhefei.webpmjava.controller;

import com.zhaochuninhefei.webpmjava.dto.Asset;
import com.zhaochuninhefei.webpmjava.dto.ResponseMsg;
import com.zhaochuninhefei.webpmjava.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhaochun
 */
@RestController
@RequestMapping("/asset")
public class AssetController {

    @Autowired
    private AssetService assetService;

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
