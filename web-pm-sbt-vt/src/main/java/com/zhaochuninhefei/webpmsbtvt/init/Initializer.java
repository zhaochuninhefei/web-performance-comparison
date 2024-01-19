package com.zhaochuninhefei.webpmsbtvt.init;

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
        log.info(String.format("%s : %s", "spring.threads.virtual.enabled", env.getProperty("spring.threads.virtual.enabled")));
    }
}
