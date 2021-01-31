package com.distributed.tracing.OrderService.consumer;

import com.distributed.tracing.OrderService.config.ApplicationProps;
import com.distributed.tracing.OrderService.dto.Order;
import com.distributed.tracing.OrderService.enums.OrderStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderListenerService {

    private static final ObjectMapper mapper = new ObjectMapper();

    private final RestTemplate restTemplate;
    private final ApplicationProps props;

    @KafkaListener(topics = "${kafka.topic-name}", groupId = "group1")
    public void listenOrders(String message) throws JsonProcessingException {

        Objects.requireNonNull(message, "Message should not be null");

        Order order = mapper.readValue(message, Order.class);

        LOGGER.info("Order received, {}", order.toString());

        restTemplate.getForEntity(String.format("%s/%s/status/%s", this.props.endpoints.getCheckoutService(), order.getId(), OrderStatus.CONFIRMED), Order.class);

        LOGGER.info("Order placed successfully, {}", order.getId());

    }

}
