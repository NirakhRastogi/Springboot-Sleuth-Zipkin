package com.distributed.tracing.OrderTrackingService.service;

import com.distributed.tracing.OrderTrackingService.config.ApplicationProps;
import com.distributed.tracing.OrderTrackingService.dto.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderTrackingTrackingServiceImpl implements IOrderTrackingService {

    private static final ObjectMapper mapper = new ObjectMapper();

    private final RestTemplate restTemplate;
    private final ApplicationProps props;

    @Override
    public Order trackOrder(String orderId) {

        Objects.requireNonNull(orderId, "OrderId should not be null");


        LOGGER.info("Fetching order details for id {}", orderId);

        Order order = restTemplate.getForEntity(String.format("%s/%s", this.props.endpoints.getCheckoutService(), orderId), Order.class).getBody();

        LOGGER.info("Order details received for id {}", orderId);

        return order;

    }

}
