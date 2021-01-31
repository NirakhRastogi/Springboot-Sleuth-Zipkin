package com.distributed.tracing.CheckoutService.service.impl;

import com.distributed.tracing.CheckoutService.enums.OrderStatus;
import com.distributed.tracing.CheckoutService.enums.PaymentStatus;
import com.distributed.tracing.CheckoutService.model.Order;
import com.distributed.tracing.CheckoutService.producer.OrderProducer;
import com.distributed.tracing.CheckoutService.repository.OrderRepository;
import com.distributed.tracing.CheckoutService.service.ICheckoutService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CheckoutServiceImpl implements ICheckoutService {

    private final OrderRepository orderRepository;

    private final OrderProducer orderProducer;

    private static final ObjectMapper mapper = new ObjectMapper();


    @Override
    public Order createOrder(Order order) {

        Objects.requireNonNull(order, "Order cannot be null");

        try {
            order.setOrderStatus(OrderStatus.PLACED);
            Order savedOrder = this.orderRepository.save(order);
            this.orderProducer.prodcuerOrder(order.getId().toString(), mapper.writeValueAsString(order));
            return savedOrder;
        } catch (Exception e) {
            LOGGER.info("Unable to place order!", e);
            return Order.builder().build();
        }
    }

    @Override
    public Order changePaymentStatus(UUID orderId, PaymentStatus paymentStatus) {
        Objects.requireNonNull(orderId, "OrderId should not be null");
        Objects.requireNonNull(paymentStatus, "Payment status should not be null");

        try {

            Optional<Order> orderOptional = this.orderRepository.findById(orderId);
            if (orderOptional.isPresent()) {
                Order order = orderOptional.get();
                order.setPaymentStatus(paymentStatus);
                return this.orderRepository.save(order);
            } else {
                LOGGER.error("Order with id {} does not exists.", orderId);
            }
        } catch (Exception e) {
            LOGGER.error("Unable to change payment status for id {}, New Status: {}", orderId, paymentStatus, e);
        }
        return null;
    }

    @Override
    public Order changeOrderStatus(UUID orderId, OrderStatus orderStatus) {
        Objects.requireNonNull(orderId, "OrderId should not be null");
        Objects.requireNonNull(orderStatus, "Order status should not be null");

        try {

            Optional<Order> orderOptional = this.orderRepository.findById(orderId);
            if (orderOptional.isPresent()) {
                Order order = orderOptional.get();
                order.setOrderStatus(orderStatus);
                return this.orderRepository.save(order);
            } else {
                LOGGER.error("Order with id {} does not exists.", orderId);
            }
        } catch (Exception e) {
            LOGGER.error("Unable to change order status for id {}, New Status: {}", orderId, orderStatus, e);
        }
        return null;
    }

    @Override
    public Order trackOrder(UUID orderId) {
        Optional<Order> orderOptional = this.orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            return orderOptional.get();
        } else {
            LOGGER.error("Order with id {} does not exists.", orderId);
            return Order.builder().build();
        }
    }
}
