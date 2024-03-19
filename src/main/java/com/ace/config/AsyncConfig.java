package com.ace.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


/**
 * @Classname: AsyncConfig
 * @Date: 20/3/2024 2:49 am
 * @Author: garlam
 * @Description:
 */

@Configuration
@EnableAsync
public class AsyncConfig {
    private static final Logger log = LogManager.getLogger(AsyncConfig.class.getName());

    @Bean("asyncTaskExecutor")
    public ThreadPoolTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20); // 设置核心线程池大小为20
        executor.setMaxPoolSize(20); // 设置最大线程池大小为20
        executor.setThreadNamePrefix("async-task-");
        executor.initialize();
        return executor;
    }
}

