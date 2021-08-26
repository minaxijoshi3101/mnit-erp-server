package com.mnit.erp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String DEFAULT_INCLUDE_PATTERN = "/.*";
    private final Logger log = LoggerFactory.getLogger(SwaggerConfig.class);

    @Bean
    public Docket swaggerSpringfoxDocket() {
        Contact contact = new Contact(
                "MNIT",
                "http://www.mnit.ac.in",
                "");

        List<VendorExtension> vext = new ArrayList<>();
        ApiInfo apiInfo = new ApiInfo(
                "MNIT ERP Backend APIs",
                "This document contains information about all the service end-points and their request/response metadata.",
                "1.0.0",
                "https://localhost:8080/mnit-erp-server",
                contact,
                "MIT",
                "https://localhost:8080/mnit-erp-server",
                vext);

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .pathMapping("/api/")
                .forCodeGeneration(true)
                .genericModelSubstitutes(ResponseEntity.class)
                .ignoredParameterTypes(Pageable.class)
                .ignoredParameterTypes(java.sql.Date.class)
                .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
                .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .useDefaultResponseMessages(false);

        docket = docket.select()
                .paths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
                .build();

        return docket;
    }


    private ApiKey apiKey() {
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(
                new SecurityReference("JWT", authorizationScopes));
    }

}
