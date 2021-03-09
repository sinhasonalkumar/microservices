package com.demo.inventory.config;


import java.net.URI;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket apiDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.demo.inventory"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo(){
        return new ApiInfo(
                "CQRS-EventSourcing Demo Axon and SpringBoot",
                "App to demonstrate CQRS-ES using Axon and Spring Boot",
                "1.0.0",
                "Terms of Service",
                new Contact("Sonal Kumar Sinha", "mycompany", "sks@mycompany.com"),
                "",
                "",
                Collections.emptyList());
    }
    
    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route(GET("/"), req ->
                ServerResponse.temporaryRedirect(URI.create("/swagger-ui/"))
                        .build());
    }

}

