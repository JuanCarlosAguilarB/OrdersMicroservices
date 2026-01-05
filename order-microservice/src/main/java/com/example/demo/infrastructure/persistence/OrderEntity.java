package com.example.demo.infrastructure.persistence;



import com.example.demo.domain.Currency;
import com.example.demo.domain.OrderStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "orders")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderEntity {

    @Id
    private Long id;
    private String customerName;
    private String customerEmail;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private Currency currency;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}