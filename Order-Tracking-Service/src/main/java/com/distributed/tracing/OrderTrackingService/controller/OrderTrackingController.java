package com.distributed.tracing.OrderTrackingService.controller;

import com.distributed.tracing.OrderTrackingService.dto.Order;
import com.distributed.tracing.OrderTrackingService.service.IOrderTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order-tracking")
public class OrderTrackingController {

    @Autowired
    private IOrderTrackingService iOrderTrackingService;

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> trackOrder(@PathVariable("orderId") String orderId) {
        return ResponseEntity.ok(this.iOrderTrackingService.trackOrder(orderId));
    }

}
