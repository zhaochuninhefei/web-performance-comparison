package cn.yjl

import cn.yjl.dto.Account
import cn.yjl.dto.Asset
import cn.yjl.dto.ResponseMsg
import cn.yjl.dto.insert
import io.github.smyrgeorge.sqlx4k.Statement
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

val ast1 = Asset(1, "asset001", "测试资产001")
val ast2 = Asset(2, "asset002", "测试资产002")
val astMp = mapOf("1" to ast1, "2" to ast2)
val assets = listOf(ast1, ast2)

fun Application.configureRouting() {
    routing {
		route("/account") {
			get("/list") {
				val statement = Statement.create("select * from accounts")

				val accountList = db.fetchAll(statement).getOrThrow()
					.map { Account.fromRow(it) }
				call.respond(accountList)
			}
			get("/query") {
				val id = (call.request.queryParameters["id"] ?: "1").toLong()
				val statement = Statement
					.create("select * from accounts where id = ?")
					.bind(0, id)

				val account = db.fetchAll(statement).getOrThrow().firstOrNull()
					?.let { Account.fromRow(it) } ?: Account()
				call.respond(account)
			}
			post("/add") {
				val account = call.receive<Account>()
				val statement = account.insert()
				val result: Result<Long> = db.execute(statement)
				val newId = result.getOrThrow()
				call.respond(ResponseMsg("1", "新增账户ID:$newId"))
			}
		}
	    route("/asset") {
			get("/list") {
				call.respond(assets)
			}
		    get("/query") {
				val id = call.request.queryParameters["id"] ?: ""
			    call.respond(astMp.getOrElse(id) { Asset() })
		    }
		    post("/modify") {
				val asset = call.receive<Asset>()
			    println("修改目标：$asset")
			    call.respond(ResponseMsg("1", "$asset"))
		    }
	    }
    }
}
