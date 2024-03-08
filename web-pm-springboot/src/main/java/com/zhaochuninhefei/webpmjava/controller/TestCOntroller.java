package com.zhaochuninhefei.webpmjava.controller;

import com.zhaochuninhefei.webpmjava.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaochun
 */
@RestController
@RequestMapping("/test")
public class TestCOntroller {
    private TestService testService;

    public TestCOntroller(TestService testService) {
        this.testService = testService;
    }

    @RequestMapping("/sql")
    public String testSql() {
        return testService.testSql();
    }
}
