package cn.yjl.operation.dto

import kotlinx.serialization.Serializable

@Serializable
data class Asset(
    var id: Int = 0,
    var name: String = "",
    var desc: String = ""
)