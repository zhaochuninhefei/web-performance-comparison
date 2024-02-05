package com.zhaochuninhefei.dbpm;

import com.zhaochuninhefei.dbpm.mariadb.MariaDBTester;
import com.zhaochuninhefei.dbpm.mysql.MySQLTester;
import com.zhaochuninhefei.dbpm.postgres.PostgresTester;
import com.zhaochuninhefei.dbpm.tidb.TiDBTester;
import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhaochun
 */
@SuppressWarnings("CallToPrintStackTrace")
public class DbpmMain {
    /**
     * DbpmMain 启动主程序
     *
     * <pre>DbpmMain参数:</pre>
     * <pre>    -type 指定运行类型</pre>
     * <pre>        s: singleThreadTest,单线程测试;</pre>
     * <pre>        p: prepareData,准备性能测试数据(用于jmeter测试前的数据准备)</pre>
     * <pre>    -database 指定数据库类型, all|mysql|mariadb|postgres|tidb, all: 所有数据库测试; mysql: 测试mysql; mariadb: 测试mariadb; postgres: 测试postgres; tidb: 测试TiDB。仅在singleThreadTest时有效</pre>
     * <pre>    -runTimes 指定测试运行次数，仅在singleThreadTest时有效</pre>
     * <pre>    -outFileName 指定输出文件路径，仅在singleThreadTest时有效</pre>
     * <pre>    -method 指定测试方法，仅在prepareData时有效,目前支持:</pre>
     * <pre>        truncateMysqlTbWarehouse/truncateMariaDBTbWarehouse/prepareMysqlDataForPT/prepareMariaDBDataForPT</pre>
     * <pre></pre>
     * <pre>使用范例:</pre>
     * <pre>    1. 单线程测试:</pre>
     * <pre>        java -jar ./target/db-pm-*-jar-with-dependencies.jar -type s -database all -runTimes 3 -outFileName out.csv</pre>
     * <pre>    2. 准备性能测试数据:</pre>
     * <pre>        java -jar ./target/db-pm-*-jar-with-dependencies.jar -type p -method truncateMysqlTbWarehouse</pre>
     *
     * @param args DbpmMain参数
     */
    public static void main(String[] args) {
        // 从args中获取参数
        Options options = getOptions();

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("DbpmMain", options);
            System.exit(1);
            return;
        }

        String type = cmd.getOptionValue("type", "s");
        database = cmd.getOptionValue("database", "all");
        runTimes = Integer.parseInt(cmd.getOptionValue("runTimes", "1"));
        outFileName = cmd.getOptionValue("outFileName", "out.csv");
        String method = cmd.getOptionValue("method", "");

        // 根据解析到的参数执行相应的操作
        if ("s".equals(type)) {
            // 单线程测试
            singleThreadTest();
        } else if ("p".equals(type)) {
            // 准备性能测试数据
            switch (method) {
                case "truncateMysqlTbWarehouse" -> truncateMysqlTbWarehouse();
                case "truncateMariaDBTbWarehouse" -> truncateMariaDBTbWarehouse();
                case "prepareMysqlDataForPT" -> prepareMysqlDataForPT();
                case "prepareMariaDBDataForPT" -> prepareMariaDBDataForPT();
                default -> {
                    System.out.println("Unsupported method: " + method);
                    formatter.printHelp("DbpmMain", options);
                    System.exit(1);
                }
            }
        } else {
            System.out.println("Unsupported type: " + type);
            formatter.printHelp("DbpmMain", options);
            System.exit(1);
        }
    }

    private static String database = "";
    private static int runTimes = 1;
    private static String outFileName = "";

    private static Options getOptions() {
        Options options = new Options();

        Option typeOption = new Option("type", true, "指定运行类型");
        typeOption.setRequired(false);
        options.addOption(typeOption);

        Option databaseOption = new Option("database", true, "指定数据库类型");
        databaseOption.setRequired(false);
        options.addOption(databaseOption);

        Option runTimesOption = new Option("runTimes", true, "指定测试运行次数");
        runTimesOption.setRequired(false);
        options.addOption(runTimesOption);

        Option outPathOption = new Option("outFileName", true, "指定输出文件名");
        outPathOption.setRequired(false);
        options.addOption(outPathOption);

        Option methodOption = new Option("method", true, "指定测试方法");
        methodOption.setRequired(false);
        options.addOption(methodOption);
        return options;
    }

    private static void singleThreadTest() {
        var prepareData = BaseTester.prepareData();
        switch (database) {
            case "mysql" -> testMysql(prepareData, runTimes, "mysql_" + outFileName);
            case "mariadb" -> testMariaDB(prepareData, runTimes, "mariadb_" + outFileName);
            case "postgres" -> testPostgres(prepareData, runTimes, "postgres_" + outFileName);
            case "tidb" -> testTiDB(prepareData, runTimes, "tidb_" + outFileName);
            case "all" -> {
                testMysql(prepareData, runTimes, "mysql_" + outFileName);
                testMariaDB(prepareData, runTimes, "mariadb_" + outFileName);
                testPostgres(prepareData, runTimes, "postgres_" + outFileName);
                testTiDB(prepareData, runTimes, "tidb_" + outFileName);
            }
        }
    }

    private static void truncateMysqlTbWarehouse() {
        var tester = new MySQLTester();
        tester.truncateTbWarehouse();
    }

    private static void truncateMariaDBTbWarehouse() {
        var tester = new MariaDBTester();
        tester.truncateTbWarehouse();
    }

    private static void prepareMysqlDataForPT() {
        var tester = new MySQLTester();
        tester.prepareDataForPT();
    }

    private static void prepareMariaDBDataForPT() {
        var tester = new MariaDBTester();
        tester.prepareDataForPT();
    }

    private static void testMysql(Map<String, Set<Map<String, Object>>> prepareData, int runTimes, String outPath) {
        System.out.println("===== 准备mysql测试, 等待10秒 =====");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("===== 开始mysql测试 =====");
        var mysqlTester = new MySQLTester();
        List<TimeDto> timeDtos = new ArrayList<>();
        for (int i = 0; i < runTimes; i++) {
            TimeDto timeDto = mysqlTester.runTester(prepareData);
            timeDtos.add(timeDto);
        }
        System.out.println(TimeDto.createInfo(timeDtos));
        writeToCsv(timeDtos, outPath);
        System.out.println("===== mysql测试结束 =====");
    }

    private static void testMariaDB(Map<String, Set<Map<String, Object>>> prepareData, int runTimes, String outPath) {
        System.out.println("===== 准备mariadb测试, 等待10秒 =====");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("===== 开始mariadb测试 =====");
        var mariaDBTester = new MariaDBTester();
        List<TimeDto> timeDtos = new ArrayList<>();
        for (int i = 0; i < runTimes; i++) {
            TimeDto timeDto = mariaDBTester.runTester(prepareData);
            timeDtos.add(timeDto);
        }
        System.out.println(TimeDto.createInfo(timeDtos));
        writeToCsv(timeDtos, outPath);
        System.out.println("===== mariadb测试结束 =====");
    }

    private static void testPostgres(Map<String, Set<Map<String, Object>>> prepareData, int runTimes, String outPath) {
        System.out.println("===== 准备postgres测试, 等待10秒 =====");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("===== 开始postgres测试 =====");
        var postgresTester = new PostgresTester();
        List<TimeDto> timeDtos = new ArrayList<>();
        for (int i = 0; i < runTimes; i++) {
            TimeDto timeDto = postgresTester.runTester(prepareData);
            timeDtos.add(timeDto);
        }
        System.out.println(TimeDto.createInfo(timeDtos));
        writeToCsv(timeDtos, outPath);
        System.out.println("==== postgres测试结束 =====");
    }

    private static void testTiDB(Map<String, Set<Map<String, Object>>> prepareData, int runTimes, String outPath) {
        System.out.println("===== 准备tidb测试, 等待10秒 =====");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("===== 开始tidb测试 =====");
        var tidbTester = new TiDBTester();
        List<TimeDto> timeDtos = new ArrayList<>();
        for (int i = 0; i < runTimes; i++) {
            TimeDto timeDto = tidbTester.runTester(prepareData);
            timeDtos.add(timeDto);
        }
        System.out.println(TimeDto.createInfo(timeDtos));
        writeToCsv(timeDtos, outPath);
        System.out.println("==== tidb测试结束 =====");
    }

    private static void writeToCsv(List<TimeDto> timeDtos, String outPath) {
        var content = TimeDto.createInfo(timeDtos);
        try (var writer = new java.io.FileWriter(outPath)) {
            writer.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
