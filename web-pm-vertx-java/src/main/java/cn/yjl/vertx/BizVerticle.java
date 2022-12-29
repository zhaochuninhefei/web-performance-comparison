package cn.yjl.vertx;

import cn.yjl.vertx.dto.Asset;
import cn.yjl.vertx.dto.ResponseMsg;
import cn.yjl.vertx.handler.AddAccountHandler;
import cn.yjl.vertx.handler.ListAccountHandler;
import cn.yjl.vertx.handler.QueryAccountHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.ResponseContentTypeHandler;
import io.vertx.mysqlclient.MySQLPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jiaolongyin
 * @description: TODO
 * @date 2022/12/28 10:50
 */
public class BizVerticle extends AbstractVerticle {

    private MySQLPool mysqlClient;

    private Asset ast1 = new Asset(1, "asset001", "测试资产001");
    private Asset ast2 = new Asset(2, "asset002", "测试资产002");

    private Map<String, Asset> astMp = new HashMap<>();

    private List<Asset> assets = new ArrayList<>();

    {
        astMp.put("1", ast1);
        astMp.put("2", ast2);
        assets.add(ast1);
        assets.add(ast2);
    }

    public BizVerticle(MySQLPool mysqlClient) {
        this.mysqlClient = mysqlClient;
    }


    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        Handler<RoutingContext> failureHandler = context -> {
            Throwable error = context.failure();
            context.end(new JsonObject().put("success", false).put("errMsg", error.getMessage()).toBuffer());
        };
        router.route("/account/*").handler(ResponseContentTypeHandler.create());
        router.route("/asset/*").handler(ResponseContentTypeHandler.create());

        router.get("/account/list").produces("application/json")
            .handler(new ListAccountHandler(mysqlClient))
            .failureHandler(failureHandler);
        router.get("/account/query").produces("application/json")
            .handler(new QueryAccountHandler(mysqlClient))
            .failureHandler(failureHandler);
        router.post("/account/add").produces("application/json")
            .handler(BodyHandler.create())
            .handler(new AddAccountHandler(mysqlClient))
            .failureHandler(failureHandler);
        router.get("/asset/list").produces("application/json")
            .handler(context -> {
                JsonArray data = new JsonArray();
                assets.stream().map(Asset::toJson).forEach(data::add);
                context.end(data.toBuffer());
            })
            .failureHandler(failureHandler);
        router.get("/asset/query").produces("application/json")
            .handler(context -> {
                String id = context.queryParams().get("id");
                Asset asset = astMp.getOrDefault(id, new Asset());
                context.end(asset.toJson().toBuffer());
            })
            .failureHandler(failureHandler);
        router.post("/asset/modify").produces("application/json")
            .handler(BodyHandler.create())
            .handler(context -> {
                JsonObject asset = context.body().asJsonObject();
                context.end(new ResponseMsg("1", asset.toString()).toJson().toBuffer());
            })
            .failureHandler(failureHandler);

        server.requestHandler(router).listen(config().getInteger("port", 8080))
            .onComplete(ar -> startPromise.handle(ar.map(r -> null)));
    }
}
