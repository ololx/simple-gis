package org.group.projects.simple.gis;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@Configuration
public class ContextConfig {


    public interface UrlService {
        String getApplicationUrl();
    }

    @Bean(name = "urlService")
    public UrlService urlService() {
        return () -> "/simple-gis";
    }

}

