package org.group.projects.simple.gis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "simple-gis",
                "simple-gis",
                "0.0.0",
                "",
                "",
                " ",
                " ");

        return apiInfo;
    }

    @Bean
    public Docket productApi() {
        Docket apiDocket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.group.projects.simple.gis.controller.api"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaData());

        return apiDocket;
    }
}
