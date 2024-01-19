package cn.yjl.helidon.service;

import cn.yjl.helidon.dto.ResponseMsg;
import cn.yjl.helidon.Util;
import cn.yjl.helidon.dto.Asset;
import io.helidon.webserver.http.HttpRules;
import io.helidon.webserver.http.HttpService;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;
import jakarta.json.JsonArray;

import java.util.List;
import java.util.Map;

public class AssetService extends BaseHttpService {

    private final Asset ast1 = new Asset(1, "asset001", "测试资产001");
    private final Asset ast2 = new Asset(2, "asset002", "测试资产002");
    private final Map<String, Asset> astMp = Map.of("1", ast1, "2", ast2);
    private final JsonArray assets = Util.toJsonArray(List.of(ast1, ast2));

    @Override
    public void routing(HttpRules rules) {
        rules.get("/list", this::listAsset)
                .get("/query", this::queryAsset)
                .post("/modify", this::modifyAsset);
    }

    private void listAsset(ServerRequest request,
                           ServerResponse response) {
        response.send(assets);
    }

    private void queryAsset(ServerRequest request,
                           ServerResponse response) {
        String id = request.query().get("id");
        response.send(astMp.get(id).toJson());
    }

    private void modifyAsset(ServerRequest request,
                            ServerResponse response) {
        Asset asset = Asset.fromEntity(request.content());
        String assetStr = asset.toString();
        response.send(new ResponseMsg("1", assetStr).toJson());
    }
}
