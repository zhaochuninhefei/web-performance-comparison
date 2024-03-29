package com.zhaochuninhefei.webpmsbtvt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhaochun
 */
@SpringBootApplication
@MapperScan("com.zhaochuninhefei.webpmsbtvt.db.dao")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
