package com.distributed.tracing.OrderService.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties
@Configuration
@Data
public class ApplicationProps {

    public Kafka kafka;
    public Endpoints endpoints;

    @Data
    public static class Kafka {
        private String bootstrapServer;
        private String topicName;
    }

    @Data
    public static class Endpoints {
        private String checkoutService;
    }

}
