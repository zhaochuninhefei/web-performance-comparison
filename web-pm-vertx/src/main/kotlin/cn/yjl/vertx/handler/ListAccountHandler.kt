package cn.yjl.vertx.handler

import cn.yjl.vertx.entity.AccountsEntity
import cn.yjl.vertx.util.toEntity
import cn.yjl.vertx.util.toJsonArray
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.coroutines.await
import io.vertx.sqlclient.SqlClient

class ListAccountHandler(private val mysqlClient: SqlClient): AbstractHandler {

    override suspend fun handle(context: RoutingContext) {
        val result = mysqlClient.preparedQuery("select * from accounts").execute().await()
        val jsonResult = result.toList().map { it.toJson() }.map { it.toEntity(AccountsEntity()) }
        context.end(jsonResult.toJsonArray().toBuffer()).await()
    }

}
