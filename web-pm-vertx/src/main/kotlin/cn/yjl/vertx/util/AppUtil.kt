package cn.yjl.vertx.util

import java.util.*

fun toCamelString(value: String): String {
	return value.split("_")
		.joinToString("") { s -> s.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } }
		.replaceFirstChar { it.lowercaseChar() }
}