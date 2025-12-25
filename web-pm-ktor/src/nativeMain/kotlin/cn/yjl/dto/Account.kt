package cn.yjl.dto

import cn.yjl.LocalDateTimeAsStringSerializer
import cn.yjl.toStr
import io.github.smyrgeorge.sqlx4k.ResultSet.Row
import io.github.smyrgeorge.sqlx4k.Statement
import io.github.smyrgeorge.sqlx4k.impl.extensions.asIntOrNull
import io.github.smyrgeorge.sqlx4k.impl.extensions.asLocalDateTimeOrNull
import io.github.smyrgeorge.sqlx4k.impl.extensions.asLongOrNull
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Account(
	var id: Long? = null,
	@Serializable(with = LocalDateTimeAsStringSerializer::class)
	var createdAt: LocalDateTime? = null,
	@Serializable(with = LocalDateTimeAsStringSerializer::class)
	var updatedAt: LocalDateTime? = null,
	@Serializable(with = LocalDateTimeAsStringSerializer::class)
	var deletedAt: LocalDateTime? = null,
	var actName: String = "",
	var actPwd: String = "",
	var actNickName: String? = null,
	var actIntroduction: String? = null,
	var actStatus: Int? = null,
	@Serializable(with = LocalDateTimeAsStringSerializer::class)
	var actRegisterDate: LocalDateTime? = null,
) {
	companion object {
		fun fromRow(row: Row): Account {
			return Account(
				row.get("id").asLongOrNull(),
				row.get("created_at").asLocalDateTimeOrNull(),
				row.get("updated_at").asLocalDateTimeOrNull(),
				row.get("deleted_at").asLocalDateTimeOrNull(),
				row.get("act_name").asStringOrNull() ?: "",
				row.get("act_pwd").asStringOrNull() ?: "",
				row.get("act_nick_name").asStringOrNull(),
				row.get("act_introduction").asStringOrNull(),
				row.get("act_status").asIntOrNull(),
				row.get("act_register_date").asLocalDateTimeOrNull(),
			)
		}
	}
}

fun Account.insert(): Statement {
	// language=SQL
	val sql = "insert into accounts(created_at, updated_at, deleted_at, act_name, act_pwd, act_nick_name, act_introduction, act_status, act_register_date) values (?, ?, ?, ?, ?, ?, ?, ?, ?);"
	val statement = Statement.create(sql)
	statement.bind(0, createdAt.toStr())
	statement.bind(1, updatedAt.toStr())
	statement.bind(2, deletedAt.toStr())
	statement.bind(3, actName)
	statement.bind(4, actPwd)
	statement.bind(5, actNickName)
	statement.bind(6, actIntroduction)
	statement.bind(7, actStatus)
	statement.bind(8, actRegisterDate.toStr())
	return statement
}

fun Account.delete(): Statement {
	// language=SQL
	val sql = "delete from accounts where id = ?;"
	val statement = Statement.create(sql)
	statement.bind(0, id)
	return statement
}