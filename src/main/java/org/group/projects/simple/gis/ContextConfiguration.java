package org.group.projects.simple.gis;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfiguration {


    public interface UrlService {
        String getApplicationUrl();
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
        return factory -> factory.setContextPath("/simple-gis");
    }

    @Bean(name = "urlService")
    public UrlService urlService() {
        return () -> "/simple-gis";
    }

}

