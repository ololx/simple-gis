package org.group.projects.simple.gis;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource({
        "classpath:application.properties",
        "classpath:hibernate-mysql.properties",
        "classpath:gis2.properties"
})
@ComponentScan({
        "org.group.projects.simple.gis.controller",
        "org.group.projects.simple.gis.service",
        "org.group.projects.simple.gis.repository"
})
public class HibernateConfig {

    @Autowired
    private Environment environment;

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));

        return dataSource;
    }

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
        Properties hibernateProperties = getHibernateProperties();

        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setPackagesToScan(new String[] {
                "org.group.projects.simple.gis.model.entity"
        });
        factoryBean.setDataSource(dataSource);
        factoryBean.setHibernateProperties(hibernateProperties);
        factoryBean.afterPropertiesSet();
        SessionFactory sessionFactory = factoryBean.getObject();

        return sessionFactory;
    }

    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager(sessionFactory);

        return hibernateTransactionManager;
    }

    @Autowired
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Autowired
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory
                = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan(new String[] {
                "org.group.projects.simple.gis.model.entity"
        });

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactory.setJpaProperties(getHibernateProperties());

        return entityManagerFactory;
    }

    private Properties getHibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect",
                environment.getProperty("spring.jpa.properties.hibernate.dialect"));
        hibernateProperties.put("hibernate.show_sql",
                environment.getProperty("spring.jpa.show-sql"));
        hibernateProperties.put("current_session_context_class",
                environment.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));
        hibernateProperties.put("hibernate.connection.pool_size",
                environment.getProperty("spring.jpa.properties.hibernate.connection.pool_size"));
        hibernateProperties.put("hibernate.connection.autocommit",
                environment.getProperty("spring.jpa.properties.hibernate.connection.autocommit"));
        hibernateProperties.put("hibernate.cache.provider_class",
                environment.getProperty("spring.jpa.properties.hibernate.cache.provider_class"));
        hibernateProperties.put("hibernate.cache.use_second_level_cache",
                environment.getProperty("spring.jpa.properties.hibernate.cache.use_second_level_cache"));
        hibernateProperties.put("hibernate.cache.use_query_cache",
                environment.getProperty("spring.jpa.properties.hibernate.cache.use_query_cache"));
        hibernateProperties.put("spring.jpa.hibernate.ddl-auto",
                environment.getProperty("spring.jpa.hibernate.ddl-auto"));

        return hibernateProperties;
    }
}
