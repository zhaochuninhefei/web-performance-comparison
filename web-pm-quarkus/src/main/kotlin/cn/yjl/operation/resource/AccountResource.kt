package cn.yjl.operation.resource

import cn.yjl.operation.dto.ResponseMsg
import cn.yjl.operation.entity.Accounts
import cn.yjl.operation.service.AccountService
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/account")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class AccountResource {

    @Inject
    lateinit var accountService: AccountService

    @GET
    @Path("/list")
    suspend fun queryAllAccounts() = accountService.queryAllAccounts()

    @GET
    @Path("/query")
    suspend fun queryAssetById(@QueryParam("id") id: Long) = accountService.queryActByID(id)

    @POST
    @Path("/add")
    suspend fun addNewAccount(accounts: Accounts): ResponseMsg {
        val newId = accountService.addNewAccount(accounts)
        return ResponseMsg("1", "新增账户ID:$newId")
    }
}