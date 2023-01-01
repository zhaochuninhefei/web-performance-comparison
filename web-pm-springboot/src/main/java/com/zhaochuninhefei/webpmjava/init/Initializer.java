package com.zhaochuninhefei.webpmjava.init;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author zhaochun
 */
@Slf4j
@Component
@SuppressWarnings("unused")
public class Initializer {
    @Autowired
    protected Environment env;

    @PostConstruct
    public void init() {
        log.info("===== 打印相关环境配置 =====");
        log.info(String.format("%s : %s", "server.tomcat.threads.max", env.getProperty("server.tomcat.threads.max")));
        log.info(String.format("%s : %s", "server.tomcat.threads.min-spare", env.getProperty("server.tomcat.threads.min-spare")));
        log.info(String.format("%s : %s", "server.tomcat.max-connections", env.getProperty("server.tomcat.max-connections")));
        log.info(String.format("%s : %s", "server.tomcat.accept-count", env.getProperty("server.tomcat.accept-count")));
    }
}
