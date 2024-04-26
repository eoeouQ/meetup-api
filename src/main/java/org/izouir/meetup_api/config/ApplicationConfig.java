package org.izouir.meetup_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("org.izouir.meetup_api")
public class ApplicationConfig {
    @Bean(name = "entityManagerFactory")
    public LocalSessionFactoryBean sessionFactory(final DataSource dataSource) {
        final var sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("org.izouir.meetup_api.entity");

        final var hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "false");
        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }
}
