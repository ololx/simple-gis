package org.group.projects.simple.gis.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket productApi() {
        Docket apiDocket = new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.group.projects.simple.gis.api.controller"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaData());

        return apiDocket;
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Simple-Gis API")
                .description("The API of Simple Geo Information System, which allows to get geo objects by request")
                .version("1.0.5")
                .contact(new Contact(
                        "Simple Projects Group",
                        "https://bitbucket.org/account/user/simple-projects-group/projects/SG",
                        "ol_lx@icloud.com"))
                .build();
    }

}
