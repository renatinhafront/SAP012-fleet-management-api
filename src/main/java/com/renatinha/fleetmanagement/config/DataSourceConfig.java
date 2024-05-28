package com.renatinha.fleetmanagement.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${DATABASE_URL}")
    private String url;

    @Value("${DATABASE_USERNAME}")
    private String username;

    @Value("${DATABASE_PASSWORD}")
    private String password;

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.postgresql.Driver");
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }
}
