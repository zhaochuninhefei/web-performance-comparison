package cn.yjl.helidon;

import jakarta.json.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

public class Util {
    public static final JsonBuilderFactory JSON_FACTORY = Json.createBuilderFactory(Collections.emptyMap());

    public static DateTimeFormatter NORMAL_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            .withZone(ZoneId.systemDefault());

    public static <T extends JsonObjectEnable> JsonArray toJsonArray(List<T> data) {
        JsonArrayBuilder builder = JSON_FACTORY.createArrayBuilder();
        data.stream().map(JsonObjectEnable::toJson).forEach(builder::add);
        return builder.build();
    }

    public static String format(LocalDateTime time) {
        if (time == null) {
            return null;
        } else {
            return time.format(Util.NORMAL_DATE_TIME_FORMATTER);
        }
    }

    public static JsonObjectBuilder addNullable(JsonObjectBuilder builder, String key, String value) {
        if (value == null) {
            builder.addNull(key);
        } else {
            builder.add(key, value);
        }
        return builder;
    }

//    public static JsonObject toJson(DbRow row) {
//        JsonObjectBuilder builder = JSON_FACTORY.createObjectBuilder();
//        row.forEach(dbColumn -> builder.add(dbColumn.name(), dbColumn.get()));
//        return builder.build();
//    }


}
