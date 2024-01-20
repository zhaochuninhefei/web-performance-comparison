package cn.yjl.helidon.service;

import cn.yjl.helidon.dto.Account;
import cn.yjl.helidon.dto.Asset;
import cn.yjl.helidon.dto.ResponseMsg;
import io.helidon.common.context.Contexts;
import io.helidon.config.Config;
import io.helidon.dbclient.DbClient;
import io.helidon.webserver.http.HttpRules;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

import java.util.Date;

import static cn.yjl.helidon.Util.JSON_FACTORY;

public class AccountService extends BaseHttpService {

    private final Config config = Config.global().get("mysql");

    private final DbClient dbClient = Contexts.globalContext()
            .get(DbClient.class)
            .orElseGet(() -> DbClient.create(config));

    @Override
    public void routing(HttpRules rules) {
        rules.get("/list", this::listAccount);
        rules.get("/query", this::queryAccount);
        rules.post("/add", this::addAccount);
    }


    private void listAccount(ServerRequest request,
                             ServerResponse response) {
        JsonArray result = super.convert(dbClient.execute().createQuery("select * from accounts").execute(), Account.class);
        response.send(result);
    }

    private void queryAccount(ServerRequest request,
                              ServerResponse response) {
        String id = request.query().get("id");
        response.send(super.convert(dbClient.execute().createQuery("select * from accounts where id = " + id).execute(), Account.class));
    }

    private void addAccount(ServerRequest request,
                            ServerResponse response) {

        JsonObject account = request.content().as(JsonObject.class);
        dbClient.execute().createInsert("""
                insert into accounts (id, created_at, updated_at, deleted_at, act_name, act_pwd, act_nick_name, act_introduction, act_status, act_register_date)
                            values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """).params(
                account.getString("createdAt", null),
                account.getString("updatedAt", null),
                account.getString("deletedAt", null),
                account.getString("actName", ""),
                account.getString("actPwd", ""),
                account.getString("actNickName", ""),
                account.getString("actIntroduction", ""),
                account.getInt("actStatus", 0),
                new Date()
        ).execute();
        response.send(new ResponseMsg("1", "新增账户ID: helidon不支持").toJson());
    }
}
