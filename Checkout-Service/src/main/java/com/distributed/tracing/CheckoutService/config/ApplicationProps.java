package com.distributed.tracing.CheckoutService.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties
@Configuration
@Data
public class ApplicationProps {

    public DatabaseConfig databaseConfig;
    public Kafka kafka;

    @Data
    public static class DatabaseConfig {
        private String username;
        private String password;
        private int port;
        private String dns;
        private String databaseName;
    }

    @Data
    public static class Kafka {
        private String bootstrapServer;
        private String topicName;
    }

}
