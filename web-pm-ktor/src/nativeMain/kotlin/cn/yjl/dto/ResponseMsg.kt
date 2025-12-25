package cn.yjl.dto

import kotlinx.serialization.Serializable

@Serializable
data class ResponseMsg(
    var resCd: String = "",
    var resMsg: String = ""
)
