package cn.yjl.operation.util

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private val DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    .withZone(ZoneId.systemDefault())

fun LocalDateTime.format(): String = this.format(DATE_TIME_FORMATTER)

fun String.toLocalDateTime(): LocalDateTime = LocalDateTime.parse(this, DATE_TIME_FORMATTER)

object LocalDateTimeAsStringSerializer : KSerializer<LocalDateTime> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("LocalDateTime", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        encoder.encodeString(value.format())
    }

    override fun deserialize(decoder: Decoder): LocalDateTime {
        return decoder.decodeString().toLocalDateTime()
    }
}