package org.group.projects.simple.gis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
public class TemplateConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean(name = "springTemplateResolver")
    @Description("Spring Template Resolver")
    public SpringResourceTemplateResolver springTemplateResolver(){
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(true);

        return templateResolver;
    }

    @Bean(name = "springTemplateEngine")
    @Description("Thymeleaf Template Engine")
    public SpringTemplateEngine springTemplateEngine(){
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.setTemplateResolver(springTemplateResolver());
        springTemplateEngine.setEnableSpringELCompiler(true);

        return springTemplateEngine;
    }

    @Bean(name = "viewResolver")
    @Description("Thymeleaf Template Resolver")
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(springTemplateEngine());
        thymeleafViewResolver.setCharacterEncoding("UTF-8");

        return thymeleafViewResolver;
    }

}
