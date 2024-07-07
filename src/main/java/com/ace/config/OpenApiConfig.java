package com.ace.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * @Classname: OpenApiConfig
 * @Date: 2023/2/24 下午 02:24
 * @Author: kalam_au
 * @Description:
 */

@Configuration
@PropertySource(value = "classpath:swagger2.properties", ignoreResourceNotFound = true, encoding = "UTF-8")
public class OpenApiConfig {
    private static final Logger log = LogManager.getLogger(OpenApiConfig.class.getName());


    @Value("${swagger.enabled}")
    private final Boolean swaggerEnabled = false;
    @Value("${docHtml.enabled}")
    private final Boolean docHtml = false;

    @Bean
    public OpenAPI customOpenAPI() {
        Contact contact = new Contact();
        contact.setEmail("garlam_au@qq.com");
        contact.setName("Garlam Au");
        return new OpenAPI()
                .info(new Info()
                        .title("Ace3 Application")
                        .version("3.0")
                        .description( "Springboot3 Application - Ace3 integrate Knife4j springdoc-openapi")
                        .contact(new Contact())
                        .termsOfService("https://AceApplication.com")
                        .license(new License().name("Apache 2.0")
                        .url("https://AceApplication.com/ace")));
    }

    @Bean
    public GroupedOpenApi winHanverkyApi() {
        return GroupedOpenApi.builder()
                .group("Win-Hanverky")
                .pathsToMatch("/rest/winhanvery/**")
                .packagesToScan("com.ace.restController.WinHanverkyRestController")
                .build();
    }

    @Bean
    public GroupedOpenApi mailApi() {
        return GroupedOpenApi.builder()
                .group("Mail")
                .pathsToMatch("/rest/mail/**")
                .build();
    }

    @Bean
    public GroupedOpenApi systemApi() {
        return GroupedOpenApi.builder()
                .group("System")
                .pathsToMatch("/rest/system/**")
                .build();
    }

    //.pathsToMatch 和 .packagesToScan 是or的關係
    @Bean
    public GroupedOpenApi aceApi() {
        return GroupedOpenApi.builder()
                .group("Ace-Application")
                .pathsToMatch("/rest/**")
                .packagesToScan("com.ace.restController.*")
                .build();
    }

}

