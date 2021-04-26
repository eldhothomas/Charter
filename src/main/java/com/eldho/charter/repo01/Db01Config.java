package com.eldho.charter.repo01;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * A configuration like this has to be created for each data source. 
 * Bean that is specified as "@Primary" will be injected into the repositories.
 * 
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "db01EntityManagerFactory", transactionManagerRef = "db01TransactionManager", basePackages = { "com.eldho.charter.repo01.repo" })
public class Db01Config {

    @Primary
    @Bean(name = "db01DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db01")
    public DataSource db01DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "db01EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean db01EntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("db01DataSource") DataSource barDataSource) {
        return builder.dataSource(barDataSource).packages("com.eldho.charter.repo01.entity").persistenceUnit("db01").build();
    }

    @Primary
    @Bean(name = "db01TransactionManager")
    public PlatformTransactionManager db01TransactionManager(@Qualifier("db01EntityManagerFactory") EntityManagerFactory barEntityManagerFactory) {
        return new JpaTransactionManager(barEntityManagerFactory);
    }

}