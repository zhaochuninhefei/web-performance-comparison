package com.zhaochuninhefei.dbpm;

import com.zhaochuninhefei.dbpm.mysql.MySQLTester;

import java.util.ArrayList;
import java.util.List;

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

        var mysqlTester = new MySQLTester();

        var prepareData = BaseTester.prepareData();

        List<TimeDto> timeDtos = new ArrayList<>();
        for (int i = 0; i < runTimes; i++) {
            TimeDto timeDto = mysqlTester.runTester(prepareData);
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
