package cn.yjl.helidon.dto;

import cn.yjl.helidon.JsonObjectEnable;
import jakarta.json.JsonObject;

import static cn.yjl.helidon.Util.JSON_FACTORY;

public record Asset(
        int id,
        String name,
        String desc
) implements JsonObjectEnable {

    public JsonObject toJson() {
        return JSON_FACTORY.createObjectBuilder()
                .add("id", id)
                .add("name", name)
                .add("desc", desc).build();
    }
}