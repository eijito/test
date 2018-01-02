package com.lhfx;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
    @Value("${swagger.host}")
    private String swaggerHost;
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
//                .host(swaggerHost)
                .apiInfo(apiInfo())
//                .pathMapping("/")
//                .directModelSubstitute(Date.class,String.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lhfx"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("工匠 APIs")
                .contact("程序猿charlie")
                .version("1.0")
                .build();
    }
}
