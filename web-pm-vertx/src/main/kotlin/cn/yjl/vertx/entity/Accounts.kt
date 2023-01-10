package cn.yjl.vertx.entity

import cn.yjl.vertx.util.LocalDateTimeAsStringSerializer
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
)
