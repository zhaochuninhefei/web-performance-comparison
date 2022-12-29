package cn.yjl.vertx.handler;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.mysqlclient.MySQLPool;

/**
 * @author jiaolongyin
 * @description: TODO
 * @date 2022/12/28 11:28
 */
public abstract class AbstractHandler implements Handler<RoutingContext> {

    protected MySQLPool mysqlClient;

    public AbstractHandler(MySQLPool mysqlClient) {
        this.mysqlClient = mysqlClient;
    }
}
