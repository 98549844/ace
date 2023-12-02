package com.ace.config;

import com.ace.api.AceApi;
import com.ace.api.Blockchain;
// import lombok.SneakyThrows;
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

    //springboot 2 call api方法
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

   // @SneakyThrows
    @Bean
    AceApi AceApi() {
        //注册local api url
        WebClient apiClient = WebClient.builder().baseUrl("http://localhost:8088").build();
        //springframework 6.1后,
        //builderFor(HttpExchangeAdapter exchangeAdapter)
        //代替builder(HttpClientAdapter clientAdapter)
        //WebClientAdapter create(WebClient webClient)
        //代替WebClientAdapter forClient(WebClient webClient)
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(apiClient)).build();
        //HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(apiClient)).build();
        return httpServiceProxyFactory.createClient(AceApi.class);
    }


   // @SneakyThrows
    @Bean
    Blockchain blockchain() {
        //注册第三方api url
        WebClient blockchain = WebClient.builder().baseUrl("https://api.tatum.io/v3/blockchain/fee").build();
        //springframework 6.1后,
        //builderFor(HttpExchangeAdapter exchangeAdapter)
        //代替builder(HttpClientAdapter clientAdapter)
        //WebClientAdapter create(WebClient webClient)
        //代替WebClientAdapter forClient(WebClient webClient)
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(blockchain)).build();
        //HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(blockchain)).build();
        return httpServiceProxyFactory.createClient(Blockchain.class);
    }
}

