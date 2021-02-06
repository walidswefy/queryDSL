package com.example.querydsl.config;


import com.example.querydsl.controller.EmployeeController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author walid.sewaify
 * @since 2020-08-03
 */
@Configuration
@EnableSwagger2
@Profile("swagger")
public class SwaggerConfig {
    @Bean
    public Docket api() {
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder().title("Simple APIs")
            .description("Interactive API documentation")
            .version("v1");

        return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false)
            .select()
            .apis(RequestHandlerSelectors.basePackage(EmployeeController.class.getPackage().getName()))
            .paths(PathSelectors.any())
            .build().apiInfo(apiInfoBuilder.build());
    }
}

