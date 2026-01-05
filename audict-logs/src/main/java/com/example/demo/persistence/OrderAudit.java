package com.example.demo.persistence;



import com.example.demo.domain.Currency;
import com.example.demo.domain.OrderStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Document("order_audits")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderAudit {

    @Id
    private Long id;
    private UUID eventId;
    private String eventType;
    private Long orderId;
    private String customerName;
    private String customerEmail;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private Currency currency;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}