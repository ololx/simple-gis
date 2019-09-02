package org.group.projects.simple.gis.api.configuration;

import com.google.common.base.Predicates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static springfox.documentation.builders.PathSelectors.regex;

@Slf4j
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.auth-server-url}")
    private String loginEndPoint;

    @Bean
    public Docket productApi() {
        Docket apiDocket = new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.group.projects.simple.gis.api.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(buildSecurityScheme()).securityContexts(buildSecurityContext())
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

    @Bean
    public SecurityConfiguration securityConfiguration() {

        Map<String, Object> additionalQueryStringParams=new HashMap<>();
        additionalQueryStringParams.put("nonce","123456");

        return SecurityConfigurationBuilder.builder()
                .clientId(clientId).realm(realm).appName("swagger-ui")
                .additionalQueryStringParams(additionalQueryStringParams)
                .build();
    }

    private List<SecurityContext> buildSecurityContext() {
        List<SecurityReference> securityReferences = new ArrayList<>();

        securityReferences.add(SecurityReference.builder().reference("oauth2").scopes(scopes().toArray(new AuthorizationScope[]{})).build());
        SecurityContext context = SecurityContext.builder().forPaths(Predicates.alwaysTrue()).securityReferences(securityReferences).build();
        List<SecurityContext> ret = new ArrayList<>();
        ret.add(context);

        return ret;
    }

    private List<? extends SecurityScheme> buildSecurityScheme() {
        List<SecurityScheme> lst = new ArrayList<>();

        LoginEndpoint login = new LoginEndpointBuilder().url(String.format(
                "%s/realms/%s/protocol/openid-connect/auth",
                loginEndPoint,
                realm))
                .build();

        List<GrantType> gTypes = new ArrayList<>();
        gTypes.add(new ImplicitGrant(login, "acces_token"));

        lst.add(new OAuth("oauth2", scopes(), gTypes));
        return lst;
    }

    private List<AuthorizationScope> scopes() {
        List<AuthorizationScope> scopes = new ArrayList<>();

        for (String scopeItem : new String[]{"openid=openid", "profile=profile"}) {
            String scope[] = scopeItem.split("=");
            if (scope.length == 2) {
                scopes.add(new AuthorizationScopeBuilder().scope(scope[0]).description(scope[1]).build());
            } else {
                log.warn("Scope '{}' is not valid (format is scope=description)", scopeItem);
            }
        }

        return scopes;
    }
}
