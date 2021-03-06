package com.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;


@Configuration
@EnableSwagger2
@PropertySource(value = "classpath:swagger2.properties", ignoreResourceNotFound = true, encoding = "UTF-8")
//@Profile("dev")
public class SwaggerConfig implements WebMvcConfigurer {

    @Value("${swagger.enabled}")
    private final Boolean swaggerEnabled = false;
    @Value("${knife4j.enabled}")
    private final Boolean knife4jEnabled = false;

    /*
     * 创建API应用
     * apiInfo() 增加API相关信息
     * select() 选择哪些路径和api会生成document
     * apis() 对所有api进行监控
     * paths() 对所有路径进行监控
     */
    @Bean
    public Docket createRestApi() {
        boolean enabled = false;
        if (swaggerEnabled || knife4jEnabled) {
            enabled = true;
        }

        return new Docket(DocumentationType.SWAGGER_2).enable(enabled).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.restController"))
                // .apis(RequestHandlerSelectors.basePackage("com.controller"))
                //.apis(SwaggerConfig.basePackage("com.controller,com.restController"))
                .paths(PathSelectors.any()).build();
    }

    @SuppressWarnings("unchecked")
    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        // return Optional.fromNullable(input.declaringClass())
        return Optional.ofNullable(input.declaringClass());
    }

    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).map(handlerPackage(basePackage)).orElse(true);
    }

    /**
     * 处理包路径配置规则,支持多路径扫描匹配以逗号隔开
     *
     * @param basePackage 扫描包路径
     * @return Function
     */
    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
        return input -> {
            for (String strPackage : basePackage.split(",")) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }


    /*
     * 创建该API的基本信息
     * title:访问界面的标题
     * description：描述
     * version：版本号
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Ace API documents (Swagger)").description("base on SpringBoot").description("前后分离框架").version("1.0").license("version1.0")
                //.licenseUrl(swagger.getLicenseUrl())
                .build();
    }
    //http://localhost:8088/swagger-ui.html


}
