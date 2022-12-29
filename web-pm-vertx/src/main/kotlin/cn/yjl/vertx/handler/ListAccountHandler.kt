package cn.yjl.vertx.handler

import cn.yjl.vertx.util.toJsonArray
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.coroutines.await
import io.vertx.mysqlclient.MySQLPool
import io.vertx.sqlclient.SqlClient

class ListAccountHandler(private val mysqlClient: MySQLPool): AbstractHandler {

    override suspend fun handle(context: RoutingContext) {
        val connection = mysqlClient.connection.await()
        val result = connection.preparedQuery("select * from accounts").execute().await()
        val jsonResult = result.toList().map { it.toJson() }
        context.end(jsonResult.toJsonArray().toBuffer()).await()
    }

}
