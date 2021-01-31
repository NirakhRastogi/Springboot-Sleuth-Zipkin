package com.distributed.tracing.CheckoutService.model;

import com.distributed.tracing.CheckoutService.dto.Item;
import com.distributed.tracing.CheckoutService.enums.OrderStatus;
import com.distributed.tracing.CheckoutService.enums.PaymentStatus;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Entity
@Table(name = "TB_ORDER")
public class Order implements Serializable {

    private static final long serialVersionUID = 6455218108450970826L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<Item> items;

    private String customerName;
    private String deliveryAddress;
    private Long deliveryPhoneNumber;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;


}
