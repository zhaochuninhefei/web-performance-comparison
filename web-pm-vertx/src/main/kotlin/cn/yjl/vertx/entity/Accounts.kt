package cn.yjl.vertx.entity

import cn.yjl.vertx.util.LocalDateTimeAsStringSerializer
import cn.yjl.vertx.util.toEntity
import io.vertx.sqlclient.Row
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class Accounts(
    var id: Long = 0L,
    var actIntroduction: String? = "",
    var actName: String? = "",
    var actNickName: String? = "",
    var actPwd: String? = "",
    @Serializable(with = LocalDateTimeAsStringSerializer::class)
    var actRegisterDate: LocalDateTime? = null,
    var actStatus: Int? = 0,
    @Serializable(with = LocalDateTimeAsStringSerializer::class)
    var createdAt: LocalDateTime? = null,
    @Serializable(with = LocalDateTimeAsStringSerializer::class)
    var deletedAt: LocalDateTime? = null,
    @Serializable(with = LocalDateTimeAsStringSerializer::class)
    var updatedAt: LocalDateTime? = null,
) {
    companion object {
        fun fromRow(row: Row): Accounts {
            val size = row.size()

            val result = Accounts()
            for (pos in 0 until size) {
                result.id = row.getLong("id")
                result.actIntroduction = row.getString("act_introduction")
                result.actName = row.getString("act_name")
                result.actNickName = row.getString("act_nick_name")
                result.actPwd = row.getString("act_pwd")
                result.actRegisterDate = row.getLocalDateTime("act_register_date")
                result.actStatus = row.getInteger("act_status")
                result.createdAt = row.getLocalDateTime("created_at")
                result.deletedAt = row.getLocalDateTime("deleted_at")
                result.updatedAt = row.getLocalDateTime("updated_at")
            }
            return result
        }
    }
}
