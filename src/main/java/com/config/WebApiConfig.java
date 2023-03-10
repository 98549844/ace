package com.config;

import com.api.AceApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.entity.Users;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;


/**
 * @Classname: WebApiConfig
 * @Date: 2023/3/10 下午 05:00
 * @Author: kalam_au
 * @Description:
 */


@Configuration
public class WebApiConfig {
    private static final Logger log = LogManager.getLogger(WebApiConfig.class.getName());

    @Bean
    WebClient webClient(ObjectMapper objectMapper) {
        return WebClient.builder().baseUrl("http://localhost:8088").build();
    }

    @SneakyThrows
    @Bean
    AceApi AceApi(WebClient webClient) {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build();
        return httpServiceProxyFactory.createClient(AceApi.class);
    }


}

