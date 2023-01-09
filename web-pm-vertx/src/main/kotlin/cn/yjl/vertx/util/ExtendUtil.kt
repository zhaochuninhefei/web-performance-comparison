package cn.yjl.vertx.util

import cn.yjl.vertx.dto.Asset
import cn.yjl.vertx.dto.ResponseMsg
import cn.yjl.vertx.entity.AbstractEntity
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.sqlclient.Row
import io.vertx.sqlclient.impl.Utils
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

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

fun <T: AbstractEntity<T>> JsonObject.toEntity(entity: T): T {
    entity.map.putAll(this.map)
    return entity
}

fun <T: AbstractEntity<T>> Row.toEntity(entity: T): T {
    val size = size()

    for (pos in 0 until size) {
        val name = getColumnName(pos)
        val value = this.getValue(pos)
        entity.put(name, Utils.toJson(value))
    }

    return entity
}

//suspend fun RoutingContext.endJson(json: Json) {
//    this.response().putHeader()
//}
