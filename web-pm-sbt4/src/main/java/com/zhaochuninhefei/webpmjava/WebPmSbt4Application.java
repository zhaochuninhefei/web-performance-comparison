package com.zhaochuninhefei.webpmjava;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhaochuninhefei.webpmjava.db.dao")
public class WebPmSbt4Application {

	public static void main(String[] args) {
		SpringApplication.run(WebPmSbt4Application.class, args);
	}

}
