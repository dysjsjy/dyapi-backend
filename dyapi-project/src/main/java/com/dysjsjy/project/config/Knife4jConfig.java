package com.dysjsjy.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile("dev")
public class Knife4jConfig {
    @Bean
    public Docket defaultApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("dyapi接口文档")
                .apiInfo(new ApiInfoBuilder()
                        .title("dysjsjy-api")
                        .description("api-backend")
                        .version("1.0")
                        .contact(new Contact("dysjsjy", "https://github.com/dysjsjy", "dysjsjy@163.com"))
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dysjsjy.project.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
