package cn.yjl.vertx.handler

import io.vertx.core.json.JsonObject
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.coroutines.await
import io.vertx.sqlclient.SqlClient
import io.vertx.sqlclient.Tuple

class QueryAccountHandler(private val mysqlClient: SqlClient): AbstractHandler {

    override suspend fun handle(context: RoutingContext) {
        val id = context.queryParam("id").getOrElse(0) { "0" }
        val result = mysqlClient.preparedQuery("select * from accounts where id = ?")
            .execute(Tuple.of(id.toLong())).await()
        val rows = result.toList()
        if (rows.isNotEmpty()) {
            context.end(rows[0].toJson().toBuffer()).await()
        } else {
            context.end(JsonObject().toBuffer()).await()
        }
    }

}
