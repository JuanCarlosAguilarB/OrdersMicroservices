package com.example.demo.sales.infrastructure.persistence;

import com.example.demo.sales.domain.Currency;
import com.example.demo.sales.domain.OrderStatus;
import com.example.demo.sales.domain.PaymentStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Table(name = "ORDERS", schema = "SALES")
@Setter
@Getter
@NoArgsConstructor
public class OrderEntity {

    @Id
    private Long id;

    private String orderNumber;
    private Long customerId;
    private OffsetDateTime orderDate;
    private String orderStatus;
    private String paymentStatus;
    private String currency;
    private BigDecimal subtotalAmount;
    private BigDecimal discountAmount;
    private BigDecimal taxAmount;
    private BigDecimal shippingAmount;
    private BigDecimal totalAmount;
    private String paymentMethod;
    private UUID shippingAddressId;
    private UUID billingAddressId;
    private String notes;
    private String source;
    private String ipAddress;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
    private Integer version;
    private boolean deleted;
    private OffsetDateTime deletedAt;
}