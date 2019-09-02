package org.group.projects.simple.gis.api.configuration;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
public class WebConfiguration {


    public interface UrlService {
        String getApplicationUrl();
    }

    @Bean(name = "webServerFactoryCustomizer")
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> getWebServerFactoryCustomizer() {
        return factory -> factory.setContextPath("/simple-gis/api");
    }

    @Bean(name = "urlService")
    public UrlService getUrlService() {
        return () -> "/simple-gis/api";
    }
}

