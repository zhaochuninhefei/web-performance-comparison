package cn.yjl.helidon.dto;

import cn.yjl.helidon.JsonObjectEnable;
import jakarta.json.JsonObject;

import static cn.yjl.helidon.Util.JSON_FACTORY;

public record ResponseMsg (
        String resCd,
        String resMsg
)  implements JsonObjectEnable {
    public JsonObject toJson() {
        return JSON_FACTORY.createObjectBuilder()
                .add("resCd", resCd)
                .add("resMsg", resMsg).build();
    }
}
