package com.ace.restController;

import com.ace.models.common.RespResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname: DefaultThreadPoolRestController
 * @Date: 13/4/2024 6:13 pm
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/threadPool")
@Tag(name = "线性池")
public class DefaultThreadPoolRestController {
    private static final Logger log = LogManager.getLogger(DefaultThreadPoolRestController.class.getName());


    //通过自动注入获取任务执行属性
    @Resource
    private TaskExecutionProperties taskExecutionProperties;


    @Operation(summary = "读取默认线性池参数")
    @GetMapping(value = "/getDefaultConfig")
    // 读取默认线程池的配置
    public RespResult readDefaultThreadPoolConfig() {

        List<String> ls = new ArrayList<>();

        // 使用ThreadPoolTaskExecutor作为默认线程池
        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
        //taskExecutionProperties.getPool().applyTo(threadPoolExecutor);

        int corePoolSize = threadPoolExecutor.getCorePoolSize();
        String corePoolSizeStr = "Core Pool Size: " + corePoolSize;
        System.out.println(corePoolSizeStr);
        ls.add(corePoolSizeStr);

        int maxPoolSize = threadPoolExecutor.getMaxPoolSize();
        String maxPoolSizeStr = "Max Pool Size: " + maxPoolSize;
        System.out.println(maxPoolSizeStr);
        ls.add(maxPoolSizeStr);

        int queueCapacity = threadPoolExecutor.getQueueCapacity();
        String queueCapacityStr = "Queue Capacity: " + queueCapacity;
        System.out.println(queueCapacityStr);
        ls.add(queueCapacityStr);

        // 使用SimpleAsyncTaskExecutor作为默认线程池
        SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();
        //  taskExecutionProperties.getPool().applyTo(simpleAsyncTaskExecutor);

        int concurrencyLimit = simpleAsyncTaskExecutor.getConcurrencyLimit();
        String concurrencyLimitStr = "SimpleAsyncTaskExecutor Concurrency Limit: " + concurrencyLimit;
        System.out.println(concurrencyLimitStr);
        ls.add(concurrencyLimitStr);

        return RespResult.success(ls);
    }

}

