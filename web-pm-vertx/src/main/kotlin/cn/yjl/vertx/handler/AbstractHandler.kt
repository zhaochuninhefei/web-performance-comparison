package cn.yjl.vertx.handler

import io.vertx.ext.web.RoutingContext

interface AbstractHandler {

    suspend fun handle(context: RoutingContext)
}
