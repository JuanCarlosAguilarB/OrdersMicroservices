package com.example.demo.sales.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
@Builder
@AllArgsConstructor
public class CreateOrderRequest {

    private Long customerId;

    private String currency;

    private BigDecimal subtotalAmount;
    private BigDecimal discountAmount;
    private BigDecimal taxAmount;
    private BigDecimal shippingAmount;

    private String paymentMethod;

    private UUID shippingAddressId;
    private UUID billingAddressId;

    private String notes;
}
