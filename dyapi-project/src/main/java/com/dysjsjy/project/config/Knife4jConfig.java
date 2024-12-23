package com.dysjsjy.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
@Profile("dev")
public class Knife4jConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("所有模块")
                .apiInfo(apiInfo("所有模块 API 文档"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dysjsjy.project.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(String description) {
        return new ApiInfoBuilder()
                .title("dysjsjy-api")
                .description(description)
                .version("1.0")
                .contact(new Contact("dysjsjy", "https://github.com/dysjsjy", "dysjsjy@163.com"))
                .build();
    }
}


//
//@Configuration
//@EnableSwagger2WebMvc
//@Profile("dev")
//public class Knife4jConfig {
//
//    @Bean
//    public Docket analysisApi() {
//        return createDocket("分析模块接口文档", AnalysisController.class);
//    }
//
//    @Bean
//    public Docket interfaceInfoApi() {
//        return createDocket("接口信息模块接口文档", InterfaceInfoController.class);
//    }
//
//    @Bean
//    public Docket postApi() {
//        return createDocket("帖子模块接口文档", PostController.class);
//    }
//
//    @Bean
//    public Docket userApi() {
//        return createDocket("用户模块接口文档", UserController.class);
//    }
//
//    @Bean
//    public Docket userInterfaceInfoApi() {
//        return createDocket("用户接口信息模块接口文档", UserInterfaceInfoController.class);
//    }
//
//    /**
//     * 统一生成 Docket
//     */
//    private Docket createDocket(String groupName, Class<?> controllerClass) {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName(groupName)
//                .apiInfo(apiInfo(groupName))
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.dysjsjy.project.controller"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)) // 只扫描 @RestController
//                .apis(input -> controllerClass.isAssignableFrom(input.declaringClass())) // 按 Controller 过滤
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    /**
//     * 公共 ApiInfo
//     */
//    private ApiInfo apiInfo(String description) {
//        return new ApiInfoBuilder()
//                .title("dysjsjy-api")
//                .description(description)
//                .version("1.0")
//                .contact(new Contact("dysjsjy", "https://github.com/dysjsjy", "dysjsjy@163.com"))
//                .build();
//    }
//}


//@Configuration
//@EnableSwagger2
//@Profile("dev")
//public class Knife4jConfig {
//    @Bean
//    public Docket defaultApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("dyapi接口文档")
//                .apiInfo(new ApiInfoBuilder()
//                        .title("dysjsjy-api")
//                        .description("api-backend")
//                        .version("1.0")
//                        .contact(new Contact("dysjsjy", "https://github.com/dysjsjy", "dysjsjy@163.com"))
//                        .build())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.dysjsjy.project.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//}