package cn.yjl.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;

import java.util.concurrent.TimeUnit;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        JsonObject mysqlConf = this.config().getJsonObject("mysql", new JsonObject());
        MySQLConnectOptions connectOptions = new MySQLConnectOptions()
            .setPort(mysqlConf.getInteger("port", 3306))
            .setHost(mysqlConf.getString("host", "127.0.0.1"))
            .setDatabase(mysqlConf.getString("db"))
            .setUser(mysqlConf.getString("user"))
            .setCharacterEncoding(mysqlConf.getString("charset", "utf8"))
            .setCollation(mysqlConf.getString("collation", "utf8_general_ci"))
            .setPassword(mysqlConf.getString("password"));
        PoolOptions poolOptions = new PoolOptions()
            .setMaxSize(mysqlConf.getInteger("poolSize", 5))
            .setIdleTimeout(mysqlConf.getInteger("idleTimeout", 3))
            .setIdleTimeoutUnit(TimeUnit.MINUTES);

        MySQLPool mysqlClient = MySQLPool.pool(vertx, connectOptions, poolOptions);
        BizVerticle bizVerticle = new BizVerticle(mysqlClient);
        vertx.deployVerticle(bizVerticle, new DeploymentOptions().setConfig(config().getJsonObject("api", new JsonObject())));
        startPromise.complete();
    }
}
