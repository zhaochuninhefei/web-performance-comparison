package cn.yjl.helidon.mapper;

import cn.yjl.helidon.dto.Account;
import io.helidon.dbclient.DbMapper;
import io.helidon.dbclient.DbRow;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountMapper implements DbMapper<Account> {

    @Override
    public Account read(DbRow row) {
        return new Account(
                row.column("id").get(BigInteger.class),
                row.column("created_at").get(LocalDateTime.class),
                row.column("updated_at").get(LocalDateTime.class),
                row.column("deleted_at").get(LocalDateTime.class),
                row.column("act_name").get(String.class),
                row.column("act_pwd").get(String.class),
                row.column("act_nick_name").get(String.class),
                row.column("act_introduction").get(String.class),
                row.column("act_status").get(Integer.class).byteValue(),
                row.column("act_register_date").get(LocalDateTime.class)
        );
    }

    @Override
    public Map<String, ?> toNamedParameters(Account value) {
        Map<String, Object> map = new HashMap<>(10);
        map.put("id", value.id());
        map.put("created_at", value.createdAt());
        map.put("updated_at", value.updatedAt());
        map.put("deleted_at", value.deletedAt());
        map.put("act_name", value.actName());
        map.put("act_pwd", value.actPwd());
        map.put("act_nick_name", value.actNickName());
        map.put("act_introduction", value.actIntroduction());
        map.put("act_status", value.actStatus());
        map.put("act_register_date", value.actRegisterDate());
        return map;
    }

    @Override
    public List<?> toIndexedParameters(Account value) {
        List<Object> list = new ArrayList<>(10);
        list.add(value.id());
        list.add(value.createdAt());
        list.add(value.updatedAt());
        list.add(value.deletedAt());
        list.add(value.actName());
        list.add(value.actPwd());
        list.add(value.actNickName());
        list.add(value.actIntroduction());
        list.add(value.actStatus());
        list.add(value.actRegisterDate());
        return list;
    }
}
