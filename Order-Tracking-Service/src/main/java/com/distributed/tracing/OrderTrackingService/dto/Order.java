package com.distributed.tracing.OrderTrackingService.dto;

import com.distributed.tracing.OrderTrackingService.enums.OrderStatus;
import com.distributed.tracing.OrderTrackingService.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = 6455218108450970826L;
    private UUID id;
    private List<Item> items;

    private String customerName;
    private String deliveryAddress;
    private Long deliveryPhoneNumber;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;

    private Date createdOn;
    private Date updatedOn;


}
