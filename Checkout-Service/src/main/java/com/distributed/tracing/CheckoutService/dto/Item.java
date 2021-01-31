package com.distributed.tracing.CheckoutService.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Item implements Serializable {

    private static final long serialVersionUID = -8066995043611381989L;
    private String id;
    private String name;
    private Double price;
    private Integer quantity;

}
