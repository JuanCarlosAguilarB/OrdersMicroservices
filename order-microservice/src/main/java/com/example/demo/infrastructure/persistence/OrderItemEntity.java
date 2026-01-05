package com.example.demo.infrastructure.persistence;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table(name = "order_items")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderItemEntity {

    @Id
    private Long id;

    private Long orderId;

    private String name;
    private String sku;

    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal subtotal;

}