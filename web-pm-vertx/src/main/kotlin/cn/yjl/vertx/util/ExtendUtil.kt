package cn.yjl.vertx.util

import cn.yjl.vertx.dto.Asset
import cn.yjl.vertx.dto.ResponseMsg
import io.vertx.core.json.Json
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.RoutingContext
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date

fun List<JsonObject>.toJsonArray(): JsonArray {
    val jsonArray = JsonArray()
    this.forEach { jsonArray.add(it) }
    return jsonArray
}

fun Asset.toJson(): JsonObject {
    return JsonObject().put("id", this.id).put("name", this.name).put("desc", this.desc)
}

fun ResponseMsg.toJson(): JsonObject {
    return JsonObject().put("resCd", this.resCd).put("resMsg", this.resMsg)
}

fun String.toInstant(): Instant? {
    return if (this == "") {
        null
    } else {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        format.parse(this).toInstant()
    }
}

fun Date.toDbString(): String {
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    return format.format(this)
}

//suspend fun RoutingContext.endJson(json: Json) {
//    this.response().putHeader()
//}
