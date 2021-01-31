package com.distributed.tracing.OrderTrackingService.service;

import com.distributed.tracing.OrderTrackingService.dto.Order;

public interface IOrderTrackingService {
    Order trackOrder(String orderId);
}
