package cn.yjl.operation.resource

import cn.yjl.operation.dto.Asset
import cn.yjl.operation.service.AssetService
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/asset")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class AssetResource {

    @Inject
    lateinit var assetService: AssetService

    @GET
    @Path("/list")
    fun queryAllAssets() = assetService.queryAllAssets()

    @GET
    @Path("/query")
    fun queryAssetById(@QueryParam("id") id: String) = assetService.queryAssetById(id)

    @POST
    @Path("/modify")
    fun modifyAsset(asset: Asset) = assetService.modifyAsset(asset)
}