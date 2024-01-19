package cn.yjl.helidon.dto;

import cn.yjl.helidon.JsonObjectEnable;
import io.helidon.http.media.ReadableEntity;
import jakarta.json.JsonObject;

import static cn.yjl.helidon.Util.JSON_FACTORY;

public record Asset(
        int id,
        String name,
        String desc
) implements JsonObjectEnable<Asset> {

    public JsonObject toJson() {
        return JSON_FACTORY.createObjectBuilder()
                .add("id", id)
                .add("name", name)
                .add("desc", desc).build();
    }

	public static Asset fromJson(JsonObject jsonObject) {
		return new Asset(
			jsonObject.getInt("id", 0),
			jsonObject.getString("name", null),
			jsonObject.getString("desc", null)
		);
	}

	public static Asset fromEntity(ReadableEntity entity) {
		return fromJson(entity.as(JsonObject.class));
	}
}