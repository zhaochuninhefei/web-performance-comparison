package cn.yjl.helidon.mapper;

import cn.yjl.helidon.dto.Account;
import io.helidon.common.Weight;
import io.helidon.dbclient.DbMapper;
import io.helidon.dbclient.spi.DbMapperProvider;

import java.util.Optional;

@Weight(100)
public class AccountMapperProvider implements DbMapperProvider {

    private static final AccountMapper MAPPER = new AccountMapper();
    @SuppressWarnings("unchecked")
    @Override
    public <T> Optional<DbMapper<T>> mapper(Class<T> type) {
        if (type.equals(Account.class)) {
            return Optional.of((DbMapper<T>) MAPPER);
        }
        return Optional.empty();
    }
}
