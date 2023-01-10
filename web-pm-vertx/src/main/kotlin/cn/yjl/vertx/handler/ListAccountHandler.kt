package cn.yjl.vertx.handler

import cn.yjl.vertx.entity.Accounts
import cn.yjl.vertx.util.end
import cn.yjl.vertx.util.toEntity
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.coroutines.await
import io.vertx.sqlclient.SqlClient

class ListAccountHandler(private val mysqlClient: SqlClient): AbstractHandler {

    override suspend fun handle(context: RoutingContext) {
        val result = mysqlClient.preparedQuery("select * from accounts").execute().await()
        val jsonResult = result.toList().map { it.toEntity<Accounts>() }
        context.end(jsonResult).await()
    }

}
