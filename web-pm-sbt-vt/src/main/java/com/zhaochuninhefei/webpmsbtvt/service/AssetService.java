package com.zhaochuninhefei.webpmsbtvt.service;

import com.zhaochuninhefei.webpmsbtvt.dto.Asset;
import com.zhaochuninhefei.webpmsbtvt.dto.ResponseMsg;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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

    public List<Asset> queryAllAssets() {
        return assets;
    }

    public Asset queryAssetById(@RequestParam String id) {
        return astMp.get(id);
    }

    public ResponseMsg modifyAsset(@RequestBody Asset asset) {
        System.out.println("修改目标: " + asset.toString());
        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.setResCd("1");
        responseMsg.setResMsg(asset.toString());
        return responseMsg;
    }
}
