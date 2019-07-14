package org.group.projects.simple.gis.configuration;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
public class WebApplicationConfiguration {


    public interface UrlService {
        String getApplicationUrl();
    }

    @Bean(name = "webServerFactoryCustomizer")
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> getWebServerFactoryCustomizer() {
        return factory -> factory.setContextPath("/simple-gis");
    }

    @Bean(name = "urlService")
    public UrlService getUrlService() {
        return () -> "/simple-gis";
    }

    @Bean(name = "filterRegistrationBean")
    public FilterRegistrationBean getFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");
        registrationBean.setFilter(characterEncodingFilter);

        return registrationBean;
    }

}

