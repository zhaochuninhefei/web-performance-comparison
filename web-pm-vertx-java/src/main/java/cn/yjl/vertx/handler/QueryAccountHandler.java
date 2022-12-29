package cn.yjl.vertx.handler;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.Tuple;

/**
 * @author jiaolongyin
 * @description: TODO
 * @date 2022/12/28 11:48
 */
public class QueryAccountHandler extends AbstractHandler {

    public QueryAccountHandler(MySQLPool mysqlClient) {
        super(mysqlClient);
    }

    @Override
    public void handle(RoutingContext context) {
        String id = context.queryParams().get("id");
        mysqlClient.preparedQuery("select id, created_at, updated_at, deleted_at, act_name, act_pwd, act_nick_name, act_introduction, act_status, act_register_date from accounts where id = ?")
            .execute(Tuple.of(Long.valueOf(id)))
            .map(rows -> {
                if (rows.size() > 0) {
                    return rows.iterator().next().toJson();
                } else {
                    return new JsonObject();
                }
            })
            .onSuccess(data -> context.end(data.toBuffer()));
    }
}
