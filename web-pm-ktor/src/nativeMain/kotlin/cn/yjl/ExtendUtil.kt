package cn.yjl

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.UtcOffset
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.char
import kotlinx.datetime.format.format
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

val CN_FORMAT = DateTimeComponents.Format {
	date(LocalDate.Formats.ISO); char(' ')
	hour(); char(':'); minute(); char(':'); second();
}

object LocalDateTimeAsStringSerializer : KSerializer<LocalDateTime?> {
	override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("LocalDateTime?", PrimitiveKind.STRING)

	override fun serialize(encoder: Encoder, value: LocalDateTime?) {
		value?.toStr()?.let {
			encoder.encodeString(it)
		}
	}

	override fun deserialize(decoder: Decoder): LocalDateTime? {
		val s: String = decoder.decodeString()
		return if (s == "null" || s.isEmpty()) {
			null
		} else {
			// 使用自定义格式解析字符串
			CN_FORMAT.parse(s).toLocalDateTime()
		}
	}
}

fun LocalDateTime?.toStr(): String? {
	return this?.let {
		CN_FORMAT.format {
			setDateTime(it)
			setOffset(UtcOffset(hours = 8))
		}
	}
}