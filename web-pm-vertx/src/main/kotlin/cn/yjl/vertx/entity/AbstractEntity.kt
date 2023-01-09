package cn.yjl.vertx.entity

import io.vertx.core.json.JsonObject

abstract class AbstractEntity<T: AbstractEntity<T>>: JsonObject() {
}
