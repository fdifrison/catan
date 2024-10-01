package com.fdifrison.catan.core.configuration;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ConfigurationPropertiesScan
public class DataSourceConfiguration {

    private String url;
    private String username;
    private String password;

    @Bean
    @ConfigurationProperties(value = "spring.datasource")
    DataSource dataSource() {
        return new DriverManagerDataSource(url, username, password);
    }
}
