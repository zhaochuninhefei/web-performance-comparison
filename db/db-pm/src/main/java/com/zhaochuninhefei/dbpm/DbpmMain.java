package com.zhaochuninhefei.dbpm;

import com.zhaochuninhefei.dbpm.mariadb.MariaDBTester;
import com.zhaochuninhefei.dbpm.mysql.MySQLTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhaochun
 */
@SuppressWarnings("CallToPrintStackTrace")
public class DbpmMain {
    public static void main(String[] args) {
        var runTimes = 1;
        if (args.length != 0) {
            try {
                runTimes = Integer.parseInt(args[0]);
            } catch (Exception e) {
                System.out.println("Usage: java -jar db-pm.jar <run times>");
                System.exit(1);
            }
        }
        var outPath = "out.csv";
        if (args.length > 1) {
            outPath = args[1];
        }

        var prepareData = BaseTester.prepareData();
//        System.out.println("===== 开始mysql测试 =====");
//        testMysql(prepareData, runTimes, "mysql_"+outPath);
//        System.out.println("===== mysql测试结束, 等待10秒 =====");
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println("===== 开始mariadb测试 =====");
        testMariaDB(prepareData, runTimes, "mariadb_"+outPath);
    }

    private static void testMysql(Map<String, Set<Map<String, Object>>> prepareData, int runTimes, String outPath) {
        var mysqlTester = new MySQLTester();
        List<TimeDto> timeDtos = new ArrayList<>();
        for (int i = 0; i < runTimes; i++) {
            TimeDto timeDto = mysqlTester.runTester(prepareData);
            timeDtos.add(timeDto);
        }
        System.out.println(TimeDto.createInfo(timeDtos));
        writeToCsv(timeDtos, outPath);
    }

    private static void testMariaDB(Map<String, Set<Map<String, Object>>> prepareData, int runTimes, String outPath) {
        var mariaDBTester = new MariaDBTester();
        List<TimeDto> timeDtos = new ArrayList<>();
        for (int i = 0; i < runTimes; i++) {
            TimeDto timeDto = mariaDBTester.runTester(prepareData);
            timeDtos.add(timeDto);
        }
        System.out.println(TimeDto.createInfo(timeDtos));
        writeToCsv(timeDtos, outPath);
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
