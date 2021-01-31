package com.distributed.tracing.CheckoutService.service;

import com.distributed.tracing.CheckoutService.enums.OrderStatus;
import com.distributed.tracing.CheckoutService.enums.PaymentStatus;
import com.distributed.tracing.CheckoutService.model.Order;

import java.util.UUID;

public interface ICheckoutService {

    Order createOrder(Order order);

    Order changePaymentStatus(UUID orderId, PaymentStatus paymentStatus);

    Order changeOrderStatus(UUID orderId, OrderStatus orderStatus);

    Order trackOrder(UUID orderId);
}
