package cn.yjl.vertx

import cn.yjl.vertx.dto.Asset
import cn.yjl.vertx.dto.ResponseMsg
import cn.yjl.vertx.handler.AddAccountHandler
import cn.yjl.vertx.handler.ListAccountHandler
import cn.yjl.vertx.handler.QueryAccountHandler
import cn.yjl.vertx.util.toJson
import cn.yjl.vertx.util.toJsonArray
import io.vertx.core.Handler
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.ext.web.handler.ResponseContentTypeHandler
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.await
import io.vertx.mysqlclient.MySQLPool
import kotlinx.coroutines.launch

class BizVerticle(private val mysqlClient: MySQLPool): CoroutineVerticle() {

    private val ast1 = Asset(1, "asset001", "测试资产001")
    private val ast2 = Asset(2, "asset002", "测试资产002")
    val astMp = mapOf("1" to ast1, "2" to ast2)
    val assets = listOf(ast1, ast2)

    override suspend fun start() {
        val server = vertx.createHttpServer()
        val router = Router.router(vertx)
        val failureHandler = Handler<RoutingContext> {
            launch {
                val error = it.failure()
                it.end(JsonObject().put("success", false).put("errMsg", error.message).toBuffer()).await()
            }
        }
        router.route("/account/*").handler(ResponseContentTypeHandler.create())
        router.route("/asset/*").handler(ResponseContentTypeHandler.create())
        router.get("/account/list").produces("application/json")
            .handler { context ->
                launch {
                    ListAccountHandler(mysqlClient).handle(context)
                }
            }
            .failureHandler(failureHandler)
        router.get("/account/query").produces("application/json")
            .handler { context ->
                launch {
                    QueryAccountHandler(mysqlClient).handle(context)
                }
            }
            .failureHandler(failureHandler)
        router.post("/account/add").produces("application/json")
            .handler(BodyHandler.create())
            .handler { context ->
                launch {
                    AddAccountHandler(mysqlClient).handle(context)
                }
            }
            .failureHandler(failureHandler)
        router.get("/asset/list").produces("application/json")
            .handler { context ->
                launch {
                    context.end(assets.map { it.toJson() }.toJsonArray().toBuffer()).await()
                }
            }
            .failureHandler(failureHandler)
        router.get("/asset/query").produces("application/json")
            .handler { context ->
                launch {
                    val id = context.queryParams().get("id")
                    context.end((astMp[id]?.toJson() ?: JsonObject()).toBuffer()).await()
                }
            }
            .failureHandler(failureHandler)
        router.post("/asset/modify").produces("application/json")
            .handler(BodyHandler.create())
            .handler { context ->
                launch {
                    val asset = context.body().asJsonObject()
                    println("修改目标：$asset")
                    context.end(ResponseMsg("1", "$asset").toJson().toBuffer()).await()
                }
            }
            .failureHandler(failureHandler)
        server.requestHandler(router).listen(config.getInteger("port", 8080)).await();
    }

    override suspend fun stop() {

    }
}
