package cn.yjl.vertx

import io.vertx.core.DeploymentOptions
import io.vertx.core.json.JsonObject
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.mysqlclient.MySQLClient
import io.vertx.mysqlclient.MySQLConnectOptions
import io.vertx.mysqlclient.MySQLPool
import io.vertx.sqlclient.PoolOptions
import java.util.concurrent.TimeUnit

class MainVerticle : CoroutineVerticle() {

    override suspend fun start() {
        val mysqlConf = config.getJsonObject("mysql", JsonObject())
        val connectOptions = MySQLConnectOptions()
            .setPort(mysqlConf.getInteger("port", 3306))
            .setHost(mysqlConf.getString("host", "127.0.0.1"))
            .setDatabase(mysqlConf.getString("db"))
            .setUser(mysqlConf.getString("user"))
            .setCharacterEncoding(mysqlConf.getString("charset", "utf8"))
            .setCollation(mysqlConf.getString("collation", "utf8_general_ci"))
            .setPassword(mysqlConf.getString("password"))

        // 连接池
        val poolOptions = PoolOptions()
            .setMaxSize(mysqlConf.getInteger("poolSize", 5))
            .setIdleTimeout(mysqlConf.getInteger("idleTimeout", 3))
            .setIdleTimeoutUnit(TimeUnit.MINUTES)

        // 创建mysql连接池
        val mysqlClient = MySQLPool.pool(vertx, connectOptions, poolOptions)

        // 部署Verticle
        vertx.deployVerticle({ BizVerticle(mysqlClient) },
            DeploymentOptions().setConfig(config.getJsonObject("api", JsonObject()))
                .setWorker(true).setInstances(Runtime.getRuntime().availableProcessors()))
    }

    override suspend fun stop() {

    }

//  override fun start(startPromise: Promise<Void>) {
//    vertx
//      .createHttpServer()
//      .requestHandler { req ->
//        req.response()
//          .putHeader("content-type", "text/plain")
//          .end("Hello from Vert.x!")
//      }
//      .listen(8888) { http ->
//        if (http.succeeded()) {
//          startPromise.complete()
//          println("HTTP server started on port 8888")
//        } else {
//          startPromise.fail(http.cause());
//        }
//      }
//  }
}
