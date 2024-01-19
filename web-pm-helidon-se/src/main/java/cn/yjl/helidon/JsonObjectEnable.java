package cn.yjl.helidon;

import jakarta.json.JsonObject;

public interface JsonObjectEnable<T extends JsonObjectEnable<T>> {
    JsonObject toJson();
}
