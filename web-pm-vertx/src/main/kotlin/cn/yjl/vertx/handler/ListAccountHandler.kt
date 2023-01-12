package cn.yjl.vertx.handler

import cn.yjl.vertx.entity.Accounts
import cn.yjl.vertx.util.end
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.coroutines.await
import io.vertx.sqlclient.SqlClient

class ListAccountHandler(private val mysqlClient: SqlClient): AbstractHandler {

    override suspend fun handle(context: RoutingContext) {
        val result = mysqlClient.preparedQuery("select * from accounts").execute().await()
        val entityList = result.toList().map { Accounts.fromRow(it) }
        context.end(entityList).await()
    }

}
