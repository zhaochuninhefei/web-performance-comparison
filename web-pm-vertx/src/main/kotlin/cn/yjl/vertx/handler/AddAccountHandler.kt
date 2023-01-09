package cn.yjl.vertx.handler

import cn.yjl.vertx.dto.ResponseMsg
import cn.yjl.vertx.entity.AccountsEntity
import cn.yjl.vertx.util.toDbString
import cn.yjl.vertx.util.toEntity
import cn.yjl.vertx.util.toJson
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.coroutines.await
import io.vertx.mysqlclient.MySQLClient
import io.vertx.sqlclient.SqlClient
import io.vertx.sqlclient.Tuple
import java.util.*

class AddAccountHandler(private val mysqlClient: SqlClient): AbstractHandler {

    override suspend fun handle(context: RoutingContext) {
        val account = context.body().asJsonObject().toEntity(AccountsEntity())
        val now = Date().toDbString()
        val params = Tuple.of(
            account.createdAt,
            account.updatedAt,
            account.deletedAt,
            account.actName,
            account.actPwd,
            account.actNickName,
            account.actIntroduction,
            account.actStatus,
            now
        )
        val result = mysqlClient.preparedQuery("""
            insert into accounts (id, created_at, updated_at, deleted_at, act_name, act_pwd, act_nick_name, act_introduction, act_status, act_register_date)
            values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """.trimIndent())
            .execute(params).await()
        val newId = result.property(MySQLClient.LAST_INSERTED_ID)
        context.end(ResponseMsg("1", "新增账户ID:$newId").toJson().toBuffer()).await()
    }

}
