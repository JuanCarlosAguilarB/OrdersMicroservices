package com.example.demo.sales.infrastructure.persistence;

import com.example.demo.sales.domain.Currency;
import com.example.demo.sales.domain.Order;
import com.example.demo.sales.domain.OrderStatus;
import com.example.demo.sales.domain.PaymentStatus;

public abstract class OrdersMapper {


    public static OrderEntity fromDomain(Order order) {
        if (order == null) {
            return null;
        }

        return OrderEntity.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .customerId(order.getCustomerId())

                .orderStatus(order.getOrderStatus() != null
                        ? order.getOrderStatus().name()
                        : null)

                .paymentStatus(order.getPaymentStatus() != null
                        ? order.getPaymentStatus().name()
                        : null)

                .currency(order.getCurrency() != null
                        ? order.getCurrency().name()
                        : null)

                .subtotalAmount(order.getSubtotalAmount())
                .discountAmount(order.getDiscountAmount())
                .taxAmount(order.getTaxAmount())
                .shippingAmount(order.getShippingAmount())
                .totalAmount(order.getTotalAmount())

                .paymentMethod(order.getPaymentMethod())
                .shippingAddressId(order.getShippingAddressId())
                .billingAddressId(order.getBillingAddressId())

                .notes(order.getNotes())
                .source(order.getSource())
                .ipAddress(order.getIpAddress())

                .orderDate(order.getOrderDate())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())

                .isActive(order.isActive())
                .version(order.getVersion())

                .build();
    }

    public static Order toDomain(OrderEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Order(
                entity.getId(),
                entity.getOrderNumber(),
                entity.getCustomerId(),
                entity.getOrderStatus() != null
                        ? OrderStatus.valueOf(entity.getOrderStatus())
                        : null,
                entity.getPaymentStatus() != null
                        ? PaymentStatus.valueOf(entity.getPaymentStatus())
                        : null,
                entity.getCurrency() != null
                        ? Currency.valueOf(entity.getCurrency())
                        : null,
                entity.getSubtotalAmount(),
                entity.getDiscountAmount(),
                entity.getTaxAmount(),
                entity.getShippingAmount(),
                entity.getTotalAmount(),
                entity.getPaymentMethod(),
                entity.getShippingAddressId(),
                entity.getBillingAddressId(),
                entity.getNotes(),
                entity.getSource(),
                entity.getIpAddress(),
                entity.getOrderDate(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.isActive(),
                entity.getVersion()
        );
    }

}



