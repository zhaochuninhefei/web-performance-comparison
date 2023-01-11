package cn.yjl.vertx.handler

import cn.yjl.vertx.entity.Accounts
import cn.yjl.vertx.util.end
import cn.yjl.vertx.util.toJsonArray
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.coroutines.await
import io.vertx.sqlclient.SqlClient
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ListAccountHandler(private val mysqlClient: SqlClient): AbstractHandler {

    override suspend fun handle(context: RoutingContext) {
        val result = mysqlClient.preparedQuery("select * from accounts").execute().await()
        val jsonString = result.toList().map { it.toJson() }.toJsonArray().toString()
        val entityList = Json.decodeFromString<List<Accounts>>(jsonString)
        context.end(entityList).await()
    }

}
