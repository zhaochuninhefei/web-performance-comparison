package cn.yjl.operation.service

import cn.yjl.operation.dto.Asset
import cn.yjl.operation.dto.ResponseMsg
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class AssetService {
    private final val ast1 = Asset(1, "asset001", "测试资产001")
    private final val ast2 = Asset(2, "asset002", "测试资产002")
    val astMp = mapOf("1" to ast1, "2" to ast2)
    val assets = listOf(ast1, ast2)

    fun queryAllAssets() = assets

    fun queryAssetById(id: String) = astMp.getOrDefault(id, Asset())

    fun modifyAsset(asset: Asset): ResponseMsg {
        println("修改目标：$asset")
        return ResponseMsg("1", "$asset")
    }
}