package com.zhaochuninhefei.dbpm.tidb;

import com.zhaochuninhefei.dbpm.BaseTester;

/**
 * @author zhaochun
 */
public class TiDBTester extends BaseTester {

    @Override
    public String getJdbcUrl() {
        return "jdbc:mysql://localhost:4000/db_pm_tidb?useUnicode=true&characterEncoding=UTF-8&useSSL=false&rewriteBatchedStatements=true";
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
