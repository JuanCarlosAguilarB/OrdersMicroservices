package com.example.demo.sales.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;


@AllArgsConstructor
@Getter
public class Order {

    private final Long id;
    private final String orderNumber;
    private final Long customerId;

    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private Currency currency;

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

    private OffsetDateTime orderDate;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    private boolean deleted;
    private Integer version;

    public void confirm() {
        if (orderStatus != OrderStatus.PENDING) {
            throw new IllegalStateException("Only pending orders can be confirmed");
        }
        this.orderStatus = OrderStatus.CONFIRMED;
    }

    public void cancel() {
        if (orderStatus == OrderStatus.COMPLETED) {
            throw new IllegalStateException("Completed orders cannot be cancelled");
        }
        this.orderStatus = OrderStatus.CANCELLED;
    }

    public void markAsPaid() {
        this.paymentStatus = PaymentStatus.PAID;
    }
}