package cn.yjl

import io.github.smyrgeorge.sqlx4k.Driver
import io.github.smyrgeorge.sqlx4k.mysql.MySQL
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.config.*
import io.ktor.server.config.yaml.*
import io.ktor.server.engine.*
import kotlin.time.Duration.Companion.minutes

fun main() {
	val port = readConfigPort()
	embeddedServer(CIO, port = port, host = "0.0.0.0", module = Application::module)
		.start(wait = true)
}

val yamlConfig = YamlConfig("./application.yaml")

fun readConfigPort(): Int {
	return yamlConfig?.config("ktor.deployment")?.tryGetString("port")?.toInt() ?: 8080
}

val db = initDb()

fun initDb(): MySQL {
	val mysqlConfig = yamlConfig?.config("ktor.mysql")
	val host = mysqlConfig?.tryGetString("host") ?: "127.0.0.1"
	val port = mysqlConfig?.tryGetString("port")?.toInt() ?: 3306
	val db = mysqlConfig?.tryGetString("db") ?: "db"
	val user = mysqlConfig?.tryGetString("root") ?: "root"
	val password = mysqlConfig?.tryGetString("password") ?: ""
	val poolSize = mysqlConfig?.tryGetString("poolSize")?.toInt() ?: 5
	val idleTimeoutMinute = mysqlConfig?.tryGetString("idleTimeoutMinute")?.toInt() ?: 3
	return MySQL(host, port, user, password, db,
		Driver.Pool.Options(maxConnections = poolSize, idleTimeout = idleTimeoutMinute.minutes))
}

fun Application.module() {
	configureSerialization()
	configureRouting()
}

