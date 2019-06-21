package com.moneybee.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class HibernateConfiguration {

    @Value("${com.moneybee.database.host}")
    private String databaseHost;

    @Value("${com.moneybee.database.port}")
    private String databasePort;

    @Value("${com.moneybee.database.name}")
    private String databaseName;

    @Value("${com.moneybee.database.user}")
    private String databaseUsername;

    @Value("${com.moneybee.database.password}")
    private String databasePassword;

    @Value("${com.moneybee.database.driverClassName}")
    private String databaseDriver;

    @Value("${com.moneybee.database.useSsl}")
    private String databaseUseSsl;

    @Value("${com.moneybee.database.timezone}")
    private String databaseTimezone;

    @Value("${com.moneybee.hibernate.dialect}")
    private String hibernateDialect;

    @Value("${com.moneybee.hibernate.show_sql}")
    private String hibernateShowSql;

    @Value("${com.moneybee.hibernate.format_sql}")
    private String hibernateFormatSql;

    @Value("${com.moneybee.hibernate.hbm2ddl.auto}")
    private String hibernateHbm2ddl;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.moneybee.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(databaseDriver);
        dataSource.setUrl("jdbc:mysql://" + databaseHost + ':'
                + databasePort+ '/'
                + databaseName + '?'
                + databaseTimezone + '&'
                + databaseUseSsl
        );
        dataSource.setUsername(databaseUsername);
        dataSource.setPassword(databasePassword);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", hibernateDialect);
        hibernateProperties.put("hibernate.show_sql", hibernateShowSql);
        hibernateProperties.put("hibernate.format_sql", hibernateFormatSql);
        hibernateProperties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddl);
        return hibernateProperties;
    }
}
