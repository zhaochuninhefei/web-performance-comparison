package com.zhaochuninhefei.dbpm.mysql;

import com.zhaochuninhefei.dbpm.TimeDto;

import java.sql.Date;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhaochun
 */
@SuppressWarnings("unused")
public class MySQLPerformanceTest {

    private static final String JDBC_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3307/db_pm_mysql?useUnicode=true&characterEncoding=UTF-8&useSSL=false&rewriteBatchedStatements=true";
    private static final String JDBC_USER = "zhaochun1";
    private static final String JDBC_PWD = "zhaochun@GITHUB";

    private final TimeDto timeDto = new TimeDto();

    public TimeDto getTimeDto() {
        return timeDto;
    }

    public static void main(String[] args) {
        MySQLPerformanceTest me = new MySQLPerformanceTest();

        Map<String, Set<Map<String, Object>>> prepareData = me.prepareData();

        me.truncateTables();

        DriverManager.drivers().forEach(driver -> System.out.println(driver.getClass().getName()));

        me.insertOrder(prepareData.get("orders"));
        me.insertCustom(prepareData.get("customs"));
        me.insertProduct(prepareData.get("products"));
        me.insertWarehouse(prepareData.get("warehouses"));

        me.selectOrders();
        me.selectCustoms();
        me.selectProducts();
        me.selectWarehouses();

        me.selectOrderJoinCustom();
        me.selectOrderJoinProduct();
        me.selectOrderJoinWarehouse();

        me.selectOrderWithOrderBy();

        System.out.println(me.getTimeDto().createStaticsInfo(true));
    }

    private Map<String, Set<Map<String, Object>>> prepareData() {
        LocalDateTime startTime = LocalDateTime.now();
        System.out.println("prepareData startTime: " + startTime);

        Map<String, Set<Map<String, Object>>> data = new HashMap<>();

        Set<Map<String, Object>> warehouses = Stream.iterate(1, t -> t <= 100, t -> t + 1).map(number -> {
            Map<String, Object> warehouse = new HashMap<>();
            warehouse.put("warehouse_number", String.format("whs_%010d", number));
            warehouse.put("warehouse_name", String.format("whs_name_%010d", number));
            return warehouse;
        }).collect(Collectors.toSet());
        data.put("warehouses", warehouses);

        Set<Map<String, Object>> products = Stream.iterate(1, t -> t <= 10000, t -> t + 1).map(number -> {
            Map<String, Object> product = new HashMap<>();
            product.put("product_number", String.format("prd_%010d", number));
            product.put("product_name", String.format("prd_name_%010d", number));
            return product;
        }).collect(Collectors.toSet());
        data.put("products", products);

        Set<Map<String, Object>> customs = Stream.iterate(1, t -> t <= 100000, t -> t + 1).map(number -> {
            Map<String, Object> custom = new HashMap<>();
            custom.put("custom_number", String.format("cst_%010d", number));
            custom.put("custom_name", String.format("cst_name_%010d", number));
            custom.put("custom_phone", String.format("180%08d", number));
            custom.put("custom_address", String.format("cst_adr_%010d", number));
            return custom;
        }).collect(Collectors.toSet());
        data.put("customs", customs);

        Set<Map<String, Object>> orders = Stream.iterate(1, t -> t <= 1000000, t -> t + 1).map(number -> {
            Map<String, Object> order = new HashMap<>();
            order.put("ord_number", String.format("ord_%010d", number));
            int cstNum = (number - 1) / 10 + 1;
            order.put("custom_number", String.format("cst_%010d", cstNum));
            int prdNum = (number - 1) / 100 + 1;
            order.put("product_number", String.format("prd_%010d", prdNum));
            int whsNum = (number - 1) / 10000 + 1;
            order.put("warehouse_number", String.format("whs_%010d", whsNum));
            order.put("ord_status", number % 2);
            order.put("order_time", new Date(new java.util.Date().getTime()));
            return order;
        }).collect(Collectors.toSet());
        data.put("orders", orders);

        LocalDateTime stopTime = LocalDateTime.now();
        System.out.println("prepareData stopTime: " + stopTime);
        Duration duration = Duration.between(startTime, stopTime);
        System.out.println("prepareData 耗时(毫秒):" + duration.toMillis());
        timeDto.setPrepareDataTime(duration.toMillis());
        return data;
    }

    private void truncateTables() {
        LocalDateTime startTime = LocalDateTime.now();
        System.out.println("truncateTables startTime: " + startTime);

        String sql_truncate_order = "truncate tb_order";
        String sql_truncate_custom = "truncate tb_custom";
        String sql_truncate_product = "truncate tb_product";
        String sql_truncate_warehouse = "truncate tb_warehouse";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PWD);
             Statement statement = connection.createStatement()
        ) {
            statement.execute(sql_truncate_order);
            statement.execute(sql_truncate_custom);
            statement.execute(sql_truncate_product);
            statement.execute(sql_truncate_warehouse);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        LocalDateTime stopTime = LocalDateTime.now();
        System.out.println("truncateTables stopTime: " + stopTime);
        Duration duration = Duration.between(startTime, stopTime);
        System.out.println("truncateTables 耗时(毫秒):" + duration.toMillis());
        timeDto.setTruncateTime(duration.toMillis());
    }

    private void insertOrder(Set<Map<String, Object>> datas) {
        List<List<Map<String, Object>>> dataGrps = splitBy1000(datas);

        String sql_insert = "insert into tb_order(`ord_number`, `custom_number`, `product_number`, `warehouse_number`, `ord_status`, `order_time`) values(?, ?, ?, ?, ?, ?)";

        LocalDateTime startTime = LocalDateTime.now();
        System.out.println("insertOrder startTime: " + startTime);
        for (List<Map<String, Object>> subDatas : dataGrps) {
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PWD);
                 PreparedStatement ps = connection.prepareStatement(sql_insert)
            ) {
                for (Map<String, Object> lineData : subDatas) {
                    ps.setObject(1, lineData.get("ord_number"));
                    ps.setObject(2, lineData.get("custom_number"));
                    ps.setObject(3, lineData.get("product_number"));
                    ps.setObject(4, lineData.get("warehouse_number"));
                    ps.setObject(5, lineData.get("ord_status"));
                    ps.setObject(6, lineData.get("order_time"));
                    ps.addBatch();
                }
                ps.executeBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        LocalDateTime stopTime = LocalDateTime.now();
        System.out.println("insertOrder stopTime: " + stopTime);
        Duration duration = Duration.between(startTime, stopTime);
        System.out.println("insertOrder 耗时(毫秒):" + duration.toMillis());
        timeDto.setInsertOrdTime(duration.toMillis());
    }

    private void insertCustom(Set<Map<String, Object>> datas) {
        List<List<Map<String, Object>>> dataGrps = splitBy1000(datas);

        String sql_insert = "insert into tb_custom(`custom_number`, `custom_name`, `custom_phone`, `custom_address`) values(?, ?, ?, ?)";

        LocalDateTime startTime = LocalDateTime.now();
        System.out.println("insertCustom startTime: " + startTime);
        for (List<Map<String, Object>> subDatas : dataGrps) {
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PWD);
                 PreparedStatement ps = connection.prepareStatement(sql_insert)
            ) {
                for (Map<String, Object> lineData : subDatas) {
                    ps.setObject(1, lineData.get("custom_number"));
                    ps.setObject(2, lineData.get("custom_name"));
                    ps.setObject(3, lineData.get("custom_phone"));
                    ps.setObject(4, lineData.get("custom_address"));
                    ps.addBatch();
                }
                ps.executeBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        LocalDateTime stopTime = LocalDateTime.now();
        System.out.println("insertCustom stopTime: " + stopTime);
        Duration duration = Duration.between(startTime, stopTime);
        System.out.println("insertCustom 耗时(毫秒):" + duration.toMillis());
        timeDto.setInsertCstTime(duration.toMillis());
    }

    private void insertProduct(Set<Map<String, Object>> datas) {
        List<List<Map<String, Object>>> dataGrps = splitBy1000(datas);

        String sql_insert = "insert into tb_product(`product_number`, `product_name`) values(?, ?)";

        LocalDateTime startTime = LocalDateTime.now();
        System.out.println("insertProduct startTime: " + startTime);
        for (List<Map<String, Object>> subDatas : dataGrps) {
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PWD);
                 PreparedStatement ps = connection.prepareStatement(sql_insert)
            ) {
                for (Map<String, Object> lineData : subDatas) {
                    ps.setObject(1, lineData.get("product_number"));
                    ps.setObject(2, lineData.get("product_name"));
                    ps.addBatch();
                }
                ps.executeBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        LocalDateTime stopTime = LocalDateTime.now();
        System.out.println("insertProduct stopTime: " + stopTime);
        Duration duration = Duration.between(startTime, stopTime);
        System.out.println("insertProduct 耗时(毫秒):" + duration.toMillis());
        timeDto.setInsertPrdTime(duration.toMillis());
    }

    private void insertWarehouse(Set<Map<String, Object>> datas) {
        List<List<Map<String, Object>>> dataGrps = splitBy1000(datas);

        String sql_insert = "insert into tb_warehouse(`warehouse_number`, `warehouse_name`) values(?, ?)";

        LocalDateTime startTime = LocalDateTime.now();
        System.out.println("insertWarehouse startTime: " + startTime);
        for (List<Map<String, Object>> subDatas : dataGrps) {
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PWD);
                 PreparedStatement ps = connection.prepareStatement(sql_insert)
            ) {
                for (Map<String, Object> lineData : subDatas) {
                    ps.setObject(1, lineData.get("warehouse_number"));
                    ps.setObject(2, lineData.get("warehouse_name"));
                    ps.addBatch();
                }
                ps.executeBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        LocalDateTime stopTime = LocalDateTime.now();
        System.out.println("insertWarehouse stopTime: " + stopTime);
        Duration duration = Duration.between(startTime, stopTime);
        System.out.println("insertWarehouse 耗时(毫秒):" + duration.toMillis());
        timeDto.setInsertWhsTime(duration.toMillis());
    }

    private void selectOrders() {
        String sql = "SELECT `id`, `ord_number`, `custom_number`, `product_number`, `warehouse_number`, `ord_status`, `order_time` FROM `tb_order`";
        long[] tmp = executeQuery(sql, "selectOrders");
        timeDto.setSelectOrdConnTime(tmp[0]);
        timeDto.setSelectOrdExecTime(tmp[1]);
        timeDto.setSelectOrdLoopTime(tmp[2]);
        timeDto.setSelectOrdAllTime(tmp[3]);
        timeDto.setOrdSize(tmp[4]);
    }

    private void selectCustoms() {
        String sql = "SELECT `id`, `custom_number`, `custom_name`, `custom_phone`, `custom_address` FROM `tb_custom`";
        long[] tmp = executeQuery(sql, "selectCustoms");
        timeDto.setSelectCstConnTime(tmp[0]);
        timeDto.setSelectCstExecTime(tmp[1]);
        timeDto.setSelectCstLoopTime(tmp[2]);
        timeDto.setSelectCstAllTime(tmp[3]);
        timeDto.setCstSize(tmp[4]);
    }

    private void selectProducts() {
        String sql = "SELECT `id`, `product_number`, `product_name` FROM `tb_product`";
        long[] tmp = executeQuery(sql, "selectProducts");
        timeDto.setSelectPrdConnTime(tmp[0]);
        timeDto.setSelectPrdExecTime(tmp[1]);
        timeDto.setSelectPrdLoopTime(tmp[2]);
        timeDto.setSelectPrdAllTime(tmp[3]);
        timeDto.setPrdSize(tmp[4]);
    }

    private void selectWarehouses() {
        String sql = "SELECT `id`, `warehouse_number`, `warehouse_name` FROM `tb_warehouse`";
        long[] tmp = executeQuery(sql, "selectWarehouses");
        timeDto.setSelectWhsConnTime(tmp[0]);
        timeDto.setSelectWhsExecTime(tmp[1]);
        timeDto.setSelectWhsLoopTime(tmp[2]);
        timeDto.setSelectWhsAllTime(tmp[3]);
        timeDto.setWhsSize(tmp[4]);
    }

    private void selectOrderJoinCustom() {
        String sql = "SELECT a.ord_number, a.ord_status, a.order_time, b.custom_number, b.custom_name FROM tb_order a inner join tb_custom b on(a.custom_number = b.custom_number)";
        long[] tmp = executeQuery(sql, "selectOrderJoinCustom");
        timeDto.setSelectOcConnTime(tmp[0]);
        timeDto.setSelectOcExecTime(tmp[1]);
        timeDto.setSelectOcLoopTime(tmp[2]);
        timeDto.setSelectOcAllTime(tmp[3]);
        timeDto.setOcSize(tmp[4]);
    }

    private void selectOrderJoinProduct() {
        String sql = "SELECT a.ord_number, a.ord_status, a.order_time, b.product_number, b.product_name FROM tb_order a inner join tb_product b on(a.product_number = b.product_number)";
        long[] tmp = executeQuery(sql, "selectOrderJoinProduct");
        timeDto.setSelectOpConnTime(tmp[0]);
        timeDto.setSelectOpExecTime(tmp[1]);
        timeDto.setSelectOpLoopTime(tmp[2]);
        timeDto.setSelectOpAllTime(tmp[3]);
        timeDto.setOpSize(tmp[4]);
    }

    private void selectOrderJoinWarehouse() {
        String sql = "SELECT a.ord_number, a.ord_status, a.order_time, b.warehouse_number, b.warehouse_name FROM tb_order a inner join tb_warehouse b on(a.warehouse_number = b.warehouse_number)";
        long[] tmp = executeQuery(sql, "selectOrderJoinWarehouse");
        timeDto.setSelectOwConnTime(tmp[0]);
        timeDto.setSelectOwExecTime(tmp[1]);
        timeDto.setSelectOwLoopTime(tmp[2]);
        timeDto.setSelectOwAllTime(tmp[3]);
        timeDto.setOwSize(tmp[4]);
    }

    private void selectOrderWithOrderBy() {
        String sql = "SELECT custom_number, product_number from tb_order order by custom_number, product_number DESC";
        long[] tmp = executeQuery(sql, "selectOrderWithOrderBy");
        timeDto.setSelectOobConnTime(tmp[0]);
        timeDto.setSelectOobExecTime(tmp[1]);
        timeDto.setSelectOobLoopTime(tmp[2]);
        timeDto.setSelectOobAllTime(tmp[3]);
        timeDto.setOobSize(tmp[4]);
    }


    private long[] executeQuery(String sql, String optName) {
        long[] returnValues = new long[5];

        LocalDateTime startTime = LocalDateTime.now();
        System.out.println(optName + " startTime: " + startTime);

        int size = 0;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PWD);
             PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            LocalDateTime connStopTime = LocalDateTime.now();
            System.out.println(optName + " conn stopTime: " + connStopTime);
            Duration duration1 = Duration.between(startTime, connStopTime);
            System.out.println(optName + " conn 耗时(毫秒):" + duration1.toMillis());
            returnValues[0] = duration1.toMillis();

            ResultSet rs = ps.executeQuery();

            LocalDateTime executeQueryStopTime = LocalDateTime.now();
            System.out.println(optName + " executeQuery stopTime: " + executeQueryStopTime);
            Duration duration2 = Duration.between(connStopTime, executeQueryStopTime);
            System.out.println(optName + " executeQuery 耗时(毫秒):" + duration2.toMillis());
            returnValues[1] = duration2.toMillis();

            while (rs.next()) {
                size++;
            }

            LocalDateTime loopStopTime = LocalDateTime.now();
            System.out.println(optName + " ResultSet遍历 stopTime: " + loopStopTime);
            Duration duration3 = Duration.between(executeQueryStopTime, loopStopTime);
            System.out.println(optName + " ResultSet遍历 耗时(毫秒):" + duration3.toMillis());
            returnValues[2] = duration3.toMillis();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LocalDateTime stopTime = LocalDateTime.now();
        System.out.println(optName + " stopTime: " + stopTime);
        Duration duration = Duration.between(startTime, stopTime);
        System.out.println(optName + " 总耗时(毫秒):" + duration.toMillis());
        System.out.println(optName + " 件数:" + size);
        returnValues[3] = duration.toMillis();
        returnValues[4] = size;

        return returnValues;
    }

    private List<List<Map<String, Object>>> splitBy1000(Set<Map<String, Object>> datas) {
        List<Map<String, Object>> lstDatas = new ArrayList<>(datas);
        List<List<Map<String, Object>>> dataGroups = new ArrayList<>();
        int remainder = lstDatas.size() % 1000;
        int grpSize = lstDatas.size() / 1000;
        grpSize = remainder == 0 ? grpSize : grpSize + 1;
        for (int i = 0; i < grpSize; i++) {
            List<Map<String, Object>> subDatas = new ArrayList<>();
            for (int j = 0; j < 1000; j++) {
                int index = i * 1000 + j;
                if (index > lstDatas.size() - 1) {
                    break;
                } else {
                    subDatas.add(lstDatas.get(index));
                }
            }
            dataGroups.add(subDatas);
        }
        return dataGroups;
    }

}
