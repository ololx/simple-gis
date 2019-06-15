package org.group.projects.simple.gis;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class ViewConfiuration implements WebMvcConfigurer, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/templates/**",
                "/css/**",
                "/js/**",
                "/txt/**"
        ).addResourceLocations(
                "/WEB-INF/templates/",
                "/WEB-INF/css/",
                "/WEB-INF/js/",
                "/WEB-INF/txt"
        );
    }

    @Description("Thymeleaf HTML View Resolver")
    @Bean(name = "htmlViewResolver")
    public ViewResolver getHtmlViewResolver() {
        ThymeleafViewResolver htmlViewResolver = new ThymeleafViewResolver();
        htmlViewResolver.setTemplateEngine(getHtmlTemplateEngine(getHtmlTemplateResolver()));
        htmlViewResolver.setContentType("text/html");
        htmlViewResolver.setCharacterEncoding("UTF-8");
        htmlViewResolver.setViewNames(new String[] {"*.html", "*.xhtml"});

        return htmlViewResolver;
    }

    @Description("Thymeleaf JS View Resolver")
    @Bean
    public ViewResolver jsViewResolver() {
        ThymeleafViewResolver jsViewResolver = new ThymeleafViewResolver();
        jsViewResolver.setTemplateEngine(getJsTemplateEngine(getJsTemplateResolver()));
        jsViewResolver.setContentType("application/javascript");
        jsViewResolver.setCharacterEncoding("UTF-8");
        jsViewResolver.setViewNames(new String[] {"*.js"});

        return jsViewResolver;
    }

    @Description("Thymeleaf CSS View Resolver")
    @Bean(name = "cssViewResolver")
    public ViewResolver getCssViewResolver() {
        ThymeleafViewResolver cssViewResolver = new ThymeleafViewResolver();
        cssViewResolver.setTemplateEngine(getCssTemplateEngine(getCssTemplateResolver()));
        cssViewResolver.setContentType("text/css");
        cssViewResolver.setCharacterEncoding("UTF-8");
        cssViewResolver.setViewNames(new String[] {"*.css"});

        return cssViewResolver;
    }

    @Description("Thymeleaf TXT View Resolver")
    @Bean(name = "txtVewResolver")
    public ViewResolver getTxtViewResolver() {
        ThymeleafViewResolver txtVewResolver = new ThymeleafViewResolver();
        txtVewResolver.setTemplateEngine(getTxtTemplateEngine(getTxtTemplateResolver()));
        txtVewResolver.setContentType("text/plain");
        txtVewResolver.setCharacterEncoding("UTF-8");
        txtVewResolver.setViewNames(new String[] {"*.txt", "*.md"});

        return txtVewResolver;
    }

    @Description("Thymeleaf HTML Template Resolver")
    @Bean(name = "htmlTemplateResolver")
    public ITemplateResolver getHtmlTemplateResolver() {
        SpringResourceTemplateResolver htmlTemplateResolver = new SpringResourceTemplateResolver();
        htmlTemplateResolver.setApplicationContext(applicationContext);
        htmlTemplateResolver.setPrefix("/WEB-INF/templates/");
        htmlTemplateResolver.setCacheable(false);
        htmlTemplateResolver.setTemplateMode(TemplateMode.HTML);

        return htmlTemplateResolver;
    }

    @Description("Thymeleaf JS Template Resolver")
    @Bean(name = "jsTemplateResolver")
    public ITemplateResolver getJsTemplateResolver() {
        SpringResourceTemplateResolver jsTemplateResolver = new SpringResourceTemplateResolver();
        jsTemplateResolver.setApplicationContext(applicationContext);
        jsTemplateResolver.setPrefix("/WEB-INF/js/");
        jsTemplateResolver.setCacheable(false);
        jsTemplateResolver.setTemplateMode(TemplateMode.JAVASCRIPT);

        return jsTemplateResolver;
    }

    @Description("Thymeleaf CSS Template Resolver")
    @Bean(name = "cssTemplateResolver")
    public ITemplateResolver getCssTemplateResolver() {
        SpringResourceTemplateResolver cssTemplateResolver = new SpringResourceTemplateResolver();
        cssTemplateResolver.setApplicationContext(applicationContext);
        cssTemplateResolver.setPrefix("/WEB-INF/css/");
        cssTemplateResolver.setCacheable(false);
        cssTemplateResolver.setTemplateMode(TemplateMode.CSS);

        return cssTemplateResolver;
    }

    @Description("Thymeleaf TXT Template Resolver")
    @Bean(name = "txtTemplateResolver")
    public ITemplateResolver getTxtTemplateResolver() {
        SpringResourceTemplateResolver plainTemplateResolver = new SpringResourceTemplateResolver();
        plainTemplateResolver.setApplicationContext(applicationContext);
        plainTemplateResolver.setPrefix("/WEB-INF/txt/");
        plainTemplateResolver.setCacheable(false);
        plainTemplateResolver.setTemplateMode(TemplateMode.TEXT);

        return plainTemplateResolver;
    }

    @Description("Spring Message Resolver")
    @Bean(name = "messageSource")
    public ResourceBundleMessageSource getMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");

        return messageSource;
    }

    @Description("Thymeleaf HTML Template Engine")
    @Qualifier("HtmlTemplateEngine")
    @Bean(name = "htmlTemplateEngine")
    public ISpringTemplateEngine getHtmlTemplateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setEnableSpringELCompiler(true);
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.setTemplateEngineMessageSource(getMessageSource());

        return templateEngine;
    }

    @Description("Thymeleaf JS Template Engine")
    @Qualifier("JsTemplateEngine")
    @Bean(name = "jsTemplateEngine")
    public ISpringTemplateEngine getJsTemplateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setEnableSpringELCompiler(true);
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.setTemplateEngineMessageSource(getMessageSource());

        return templateEngine;
    }

    @Description("Thymeleaf CSS Template Engine")
    @Qualifier("CssTemplateEngine")
    @Bean(name = "cssTemplateEngine")
    public ISpringTemplateEngine getCssTemplateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setEnableSpringELCompiler(true);
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.setTemplateEngineMessageSource(getMessageSource());

        return templateEngine;
    }

    @Description("Thymeleaf TXT Template Engine")
    @Qualifier("TxtTemplateEngine")
    @Bean(name = "txtTemplateEngine")
    public ISpringTemplateEngine getTxtTemplateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setEnableSpringELCompiler(true);
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.setTemplateEngineMessageSource(getMessageSource());

        return templateEngine;
    }

    /*@Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("en"));
        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }*/
}
