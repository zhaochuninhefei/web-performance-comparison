package cn.yjl.helidon.dto;

import cn.yjl.helidon.JsonObjectEnable;
import cn.yjl.helidon.Util;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

import java.math.BigInteger;
import java.time.LocalDateTime;

import static cn.yjl.helidon.Util.JSON_FACTORY;

public record Account(
        BigInteger id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt,
        String actName,
        String actPwd,
        String actNickName,
        String actIntroduction,
        Byte actStatus,
        LocalDateTime actRegisterDate
) implements JsonObjectEnable<Account> {
    public JsonObject toJson() {
        JsonObjectBuilder builder = JSON_FACTORY.createObjectBuilder()
                .add("id", id);
        Util.addNullable(builder, "createdAt", Util.format(createdAt));
        Util.addNullable(builder, "updatedAt", Util.format(updatedAt));
        Util.addNullable(builder, "deletedAt", Util.format(deletedAt));
        Util.addNullable(builder, "actName", actName);
        Util.addNullable(builder, "actPwd", actPwd);
        Util.addNullable(builder, "actNickName", actNickName);
        Util.addNullable(builder, "actIntroduction", actIntroduction);
        builder.add("actStatus", actStatus);
        Util.addNullable(builder, "actRegisterDate", Util.format(actRegisterDate));
        return builder.build();
    }
}
