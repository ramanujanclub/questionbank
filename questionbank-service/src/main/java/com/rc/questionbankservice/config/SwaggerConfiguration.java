package com.rc.questionbankservice.config;


import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

/**
 * <ul>
 * <li>Title: SwaggerConfiguration.java</li>
 * <li>Description: The class <code>SwaggerConfiguration</code> ... </li>
 * </ul>
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends WebMvcConfigurationSupport {

    @Value("${swagger.base.package}")
    private String swaggerBasePackage;

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String JWT_REFERENCE = "JWT";

    /**
     * Our swagger docket.
     *
     * @return A docket.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(createBasicApiInfo())//basic info of Api
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerBasePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo createBasicApiInfo() {
        return new ApiInfo(
                "QuestionBank Service API",
                "API for QuestionBank Service",
                "V1",
                null,
                null,
                null,
                null,
                Collections.emptyList());
    }

    private ApiKey apiKey() {
        return new ApiKey(JWT_REFERENCE, AUTHORIZATION_HEADER, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/api/.*"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference(JWT_REFERENCE, authorizationScopes));
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
