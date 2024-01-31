package com.zhaochuninhefei.dbpm.mysql;

import com.zhaochuninhefei.dbpm.BaseTester;

/**
 * @author zhaochun
 */
public class MySQLTester extends BaseTester {

    @Override
    public String getJdbcUrl() {
        return "jdbc:mysql://localhost:3307/db_pm_mysql?useUnicode=true&characterEncoding=UTF-8&useSSL=false&rewriteBatchedStatements=true";
    }

    @Override
    public String getJdbcUsername() {
        return "zhaochun1";
    }

    @Override
    public String getJdbcPassword() {
        return "zhaochun@GITHUB";
    }

    @Override
    public String getJdbcDriverName() {
        return "com.mysql.cj.jdbc.Driver";
    }
}
