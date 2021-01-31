package com.distributed.tracing.CheckoutService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PostgresConfig {

    @Autowired
    private ApplicationProps props;

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url(String.format("jdbc:postgresql://%s:%d/%s", props.databaseConfig.getDns(), props.databaseConfig.getPort(), props.databaseConfig.getDatabaseName()))
                .username(props.databaseConfig.getUsername())
                .password(props.databaseConfig.getPassword())
                .build();

    }

}
