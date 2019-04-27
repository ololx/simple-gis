package org.group.projects.simple.gis;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.MultipartConfigElement;

@Configuration
public class ApplicationConfig {

    @Bean
    MultipartConfigElement multipartConfigElement() {
       MultipartConfigFactory factory = new MultipartConfigFactory();
       factory.setMaxFileSize("256MB");
       factory.setMaxRequestSize("256MB");

       return factory.createMultipartConfig();
    }
}
