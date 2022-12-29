package cn.yjl.vertx.util;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author jiaolongyin
 * @description: TODO
 * @date 2022/12/28 11:35
 */
public class AppUtil {

    public static JsonArray toJsonArray(List<JsonObject> listJson) {
        JsonArray array = new JsonArray();
        listJson.forEach(array::add);
        return array;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
