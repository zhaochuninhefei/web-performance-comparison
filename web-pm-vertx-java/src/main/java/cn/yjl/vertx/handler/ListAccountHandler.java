package cn.yjl.vertx.handler;

import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.RoutingContext;
import io.vertx.mysqlclient.MySQLPool;

/**
 * @author jiaolongyin
 * @description: TODO
 * @date 2022/12/28 11:29
 */
public class ListAccountHandler extends AbstractHandler {

    public ListAccountHandler(MySQLPool mysqlClient) {
        super(mysqlClient);
    }

    @Override
    public void handle(RoutingContext context) {
        mysqlClient
                .preparedQuery("select id, created_at, updated_at, deleted_at, act_name, act_pwd, act_nick_name, act_introduction, act_status, act_register_date from accounts")
                .execute()
                .map(rows -> {
                    JsonArray array = new JsonArray();
                    rows.forEach(row -> array.add(row.toJson()));
                    return array;
                })
                .onSuccess(array -> context.end(array.toBuffer()));
    }
}
