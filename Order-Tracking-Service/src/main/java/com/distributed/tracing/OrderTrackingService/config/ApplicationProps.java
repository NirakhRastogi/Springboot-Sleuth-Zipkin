package com.distributed.tracing.OrderTrackingService.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties
@Configuration
@Data
public class ApplicationProps {

    public Endpoints endpoints;

    @Data
    public static class Endpoints {
        private String checkoutService;
    }

}
