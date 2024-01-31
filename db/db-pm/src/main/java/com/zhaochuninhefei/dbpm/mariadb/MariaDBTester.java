package com.zhaochuninhefei.dbpm.mariadb;

import com.zhaochuninhefei.dbpm.BaseTester;
import com.zhaochuninhefei.dbpm.TimeDto;

import java.util.Map;
import java.util.Set;

/**
 * @author zhaochun
 */
public class MariaDBTester extends BaseTester {
    protected static final String SQL_CREATE_INDEX_TB_PRODUCT_IDX01 = "CREATE INDEX `tb_product_idx01` ON `tb_product` (`product_number`)";
    protected static final String SQL_DROP_INDEX_TB_PRODUCT_IDX01 = "DROP INDEX `tb_product_idx01` ON `tb_product`";

    @Override
    public String getJdbcUrl() {
        return "jdbc:mariadb://localhost:3308/db_pm_mariadb?useUnicode=true&characterEncoding=UTF-8&useSSL=false&rewriteBatchedStatements=true";
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
        return "org.mariadb.jdbc.Driver";
    }

    @Override
    public TimeDto runTester(Map<String, Set<Map<String, Object>>> prepareData) {
        try {
            Class.forName(getJdbcDriverName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        timeDto = new TimeDto();

        this.truncateTables();
        this.executeDDL(SQL_CREATE_INDEX_TB_PRODUCT_IDX01);

        this.insertOrder(prepareData.get("orders"));
        this.insertCustom(prepareData.get("customs"));
        this.insertProduct(prepareData.get("products"));
        this.insertWarehouse(prepareData.get("warehouses"));

        this.selectOrders();
        this.selectCustoms();
        this.selectProducts();
        this.selectWarehouses();

        this.selectOrderJoinCustom();
        this.selectOrderJoinProduct();
        this.selectOrderJoinWarehouse();

        this.executeDDL(SQL_CREATE_INDEX_TB_ORDER_IDX01);
        this.selectOrderWithOrderBy();
        this.executeDDL(SQL_DROP_INDEX_TB_ORDER_IDX01);

        this.executeDDL(SQL_DROP_INDEX_TB_PRODUCT_IDX01);

        return timeDto;
    }
}
