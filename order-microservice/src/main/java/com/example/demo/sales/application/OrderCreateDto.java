package com.example.demo.sales.application;

import com.example.demo.sales.domain.Currency;
import com.example.demo.sales.domain.Order;
import com.example.demo.sales.domain.OrderStatus;
import com.example.demo.sales.domain.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.time.OffsetDateTime;
import java.util.UUID;

@Value
@Builder
@Getter
public class OrderCreateDto {

    Long customerId;
    Currency currency;

    BigDecimal subtotalAmount;
    BigDecimal discountAmount;
    BigDecimal taxAmount;
    BigDecimal shippingAmount;

    String paymentMethod;

    UUID shippingAddressId;
    UUID billingAddressId;

    String notes;

    InetAddress ipAddress;
    String source;

    public static Order toDomain(OrderCreateDto dto) {

        BigDecimal totalAmount =
                dto.getSubtotalAmount()
                        .subtract(dto.getDiscountAmount() != null ? dto.getDiscountAmount() : BigDecimal.ZERO)
                        .add(dto.getTaxAmount() != null ? dto.getTaxAmount() : BigDecimal.ZERO)
                        .add(dto.getShippingAmount() != null ? dto.getShippingAmount() : BigDecimal.ZERO);

        OffsetDateTime now = OffsetDateTime.now();

        return new Order(
                null,
                "default", // TODO; adjust it
                dto.getCustomerId(),
                OrderStatus.PENDING,
                PaymentStatus.PENDING,
                dto.getCurrency(),
                dto.getSubtotalAmount(),
                dto.getDiscountAmount(),
                dto.getTaxAmount(),
                dto.getShippingAmount(),
                totalAmount,
                dto.getPaymentMethod(),
                dto.getShippingAddressId(),
                dto.getBillingAddressId(),
                dto.getNotes(),
                dto.getSource(),
                dto.getIpAddress(),
                now,
                now,
                now,
                true,
                0
        );
    }
}
