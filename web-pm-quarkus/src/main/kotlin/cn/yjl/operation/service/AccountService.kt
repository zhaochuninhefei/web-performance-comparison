package cn.yjl.operation.service

import cn.yjl.operation.entity.Accounts
import io.smallrye.mutiny.coroutines.awaitSuspending
import java.time.LocalDateTime
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class AccountService {

    suspend fun queryAllAccounts() = Accounts.listAll().awaitSuspending()

    suspend fun queryActByID(id: Long) = Accounts.findById(id).awaitSuspending() ?: Accounts()

    suspend fun addNewAccount(accounts: Accounts): Long {
        accounts.actRegisterDate = LocalDateTime.now()
        return accounts.persistAndFlush<Accounts>().awaitSuspending().id ?: 0L
    }
}