package com.ace.config;

import com.ace.api.AceApi;
import com.ace.api.Daatm;
import com.ace.api.EthGasStation;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    //注册local api
/*    @Bean(name = "webClient")
    WebClient webClient(ObjectMapper objectMapper) {
        return WebClient.builder()
                .baseUrl("http://localhost:8088")
                .build();
    }*/

    //注册第三方api
/*    @Bean(name = "daatmWebClient")
    WebClient daatmWebClient(ObjectMapper objectMapper) {
        return WebClient.builder()
                .baseUrl("http://svrtest000.mmatrix.io")
                .build();
    }*/

    @SneakyThrows
    @Bean
    AceApi AceApi() {
        //注册local api url
        WebClient apiClient = WebClient.builder().baseUrl("http://localhost:8088").build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(apiClient)).build();
        return httpServiceProxyFactory.createClient(AceApi.class);
    }

    @SneakyThrows
    @Bean
    Daatm DaatmApi() {
        //注册第三方api url
        WebClient daatmClient = WebClient.builder().baseUrl("http://svrtest000.mmatrix.io").build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(daatmClient)).build();
        return httpServiceProxyFactory.createClient(Daatm.class);
    }


    @SneakyThrows
    @Bean
    EthGasStation EthGasStationApi() {
        //注册第三方api url
        WebClient daatmClient = WebClient.builder().baseUrl("https://api.ethgasstation.info").build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(daatmClient)).build();
        return httpServiceProxyFactory.createClient(EthGasStation.class);
    }
}

