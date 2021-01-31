package com.distributed.tracing.OrderService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {

    private static final long serialVersionUID = -8066995043611381989L;
    private String id;
    private String name;
    private Double price;
    private Integer quantity;

}
