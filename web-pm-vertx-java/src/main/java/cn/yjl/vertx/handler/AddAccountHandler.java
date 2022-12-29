package cn.yjl.vertx.handler;

import cn.yjl.vertx.dto.ResponseMsg;
import cn.yjl.vertx.util.AppUtil;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.mysqlclient.MySQLClient;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.Tuple;

import java.util.Date;

/**
 * @author jiaolongyin
 * @description: TODO
 * @date 2022/12/28 11:29
 */
public class AddAccountHandler extends AbstractHandler {

    public AddAccountHandler(MySQLPool mysqlClient) {
        super(mysqlClient);
    }

    @Override
    public void handle(RoutingContext context) {
        JsonObject account = context.body().asJsonObject();
        String now = AppUtil.formatDate(new Date());
        Tuple params = Tuple.of(
            account.getString("createdAt", null),
            account.getString("updatedAt", null),
            account.getString("deletedAt", null),
            account.getString("actName", ""),
            account.getString("actPwd", ""),
            account.getString("actNickName"),
            account.getString("actIntroduction"),
            account.getInteger("actStatus"),
            now
        );
        mysqlClient.preparedQuery("insert into accounts " +
                "(id, created_at, updated_at, deleted_at, act_name, act_pwd, act_nick_name, act_introduction, act_status, act_register_date) " +
                "values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
            .execute(params).map(result -> result.property(MySQLClient.LAST_INSERTED_ID))
            .onSuccess(newId -> context.end(new ResponseMsg("1", "新增账户ID:" + newId).toJson().toBuffer()));
    }
}
