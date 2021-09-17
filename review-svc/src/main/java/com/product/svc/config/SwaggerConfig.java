package com.product.svc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.title}")
    private String swaggerTitle;

    @Value("${swagger.description}")
    private String swaggerDescription;

    @Value("${swagger.version}")
    private String swaggerVersion;

    @Value("${swagger.contextPath}")
    private String contextPath;


    /*********************************************************
    * Initialise Swagger setup
    *********************************************************/
    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(
                        new ApiInfoBuilder().title(swaggerTitle)
                                .description(swaggerDescription)
                                .version(swaggerVersion)
                                .build()
                )
                .pathProvider(new RelativePathProvider(null) {
                    @Override
                    public String getApplicationBasePath() {
                        return contextPath;
                    }
                });
    }

}
