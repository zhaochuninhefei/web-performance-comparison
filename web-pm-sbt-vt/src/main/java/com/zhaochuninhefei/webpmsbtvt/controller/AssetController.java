package com.zhaochuninhefei.webpmsbtvt.controller;

import com.zhaochuninhefei.webpmsbtvt.dto.Asset;
import com.zhaochuninhefei.webpmsbtvt.dto.ResponseMsg;
import com.zhaochuninhefei.webpmsbtvt.service.AssetService;
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
