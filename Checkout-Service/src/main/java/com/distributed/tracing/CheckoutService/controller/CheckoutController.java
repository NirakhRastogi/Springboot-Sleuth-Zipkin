package com.distributed.tracing.CheckoutService.controller;

import com.distributed.tracing.CheckoutService.enums.OrderStatus;
import com.distributed.tracing.CheckoutService.enums.PaymentStatus;
import com.distributed.tracing.CheckoutService.model.Order;
import com.distributed.tracing.CheckoutService.service.ICheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class CheckoutController {

    @Autowired
    private ICheckoutService checkoutService;

    @PostMapping("/")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok(this.checkoutService.createOrder(order));
    }

    @GetMapping("/{orderId}/payment/status/{status}")
    public ResponseEntity<Order> createPaymentStatus(@PathVariable("orderId") UUID orderId, @PathVariable("status") PaymentStatus paymentStatus) {
        return ResponseEntity.ok(this.checkoutService.changePaymentStatus(orderId, paymentStatus));
    }

    @GetMapping("/{orderId}/status/{status}")
    public ResponseEntity<Order> changeOrderStatus(@PathVariable("orderId") UUID orderId, @PathVariable("status") OrderStatus orderStatus) {
        return ResponseEntity.ok(this.checkoutService.changeOrderStatus(orderId, orderStatus));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> trackOrder(@PathVariable("orderId") UUID orderId) {
        return ResponseEntity.ok(this.checkoutService.trackOrder(orderId));
    }


}
