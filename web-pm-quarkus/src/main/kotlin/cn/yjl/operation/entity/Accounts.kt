package cn.yjl.operation.entity

import cn.yjl.operation.util.LocalDateTimeAsStringSerializer
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheEntityBase
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "accounts")
@Serializable
open class Accounts : PanacheEntityBase {

    companion object : PanacheCompanion<Accounts> {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    open var id: Long? = null

    @Column(name = "created_at", columnDefinition = "datetime(3)")
    @Serializable(with = LocalDateTimeAsStringSerializer::class)
    open var createdAt: LocalDateTime? = null

    @Column(name = "updated_at", columnDefinition = "datetime(3)")
    @Serializable(with = LocalDateTimeAsStringSerializer::class)
    open var updatedAt: LocalDateTime? = null

    @Column(name = "deleted_at", columnDefinition = "datetime(3)")
    @Serializable(with = LocalDateTimeAsStringSerializer::class)
    open var deletedAt: LocalDateTime? = null

    @Column(name = "act_name", length = 100, nullable = false)
    open var actName: String = ""

    @Column(name = "act_pwd", length = 255, nullable = false)
    open var actPwd: String = ""

    @Column(name = "act_nick_name", length = 255)
    open var actNickName: String? = null

    @Column(name = "act_introduction", length = 255)
    open var actIntroduction: String? = null

    @Column(name = "act_status", columnDefinition = "tinyint")
    open var actStatus: Int? = null

    @Column(name = "act_register_date", columnDefinition = "datetime(3)")
    @Serializable(with = LocalDateTimeAsStringSerializer::class)
    open var actRegisterDate: LocalDateTime? = null
}