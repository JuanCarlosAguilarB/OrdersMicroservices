package com.example.demo.infrastructure.persistence;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;

import java.util.List;

public class OrderMapper {

    public static OrderEntity toEntity(Order order) {
        return OrderEntity.builder()
                .id(order.getId())
                .customerEmail(order.getCustomerEmail())
                .status(order.getStatus())
                .currency(order.getCurrency())
                .totalAmount(order.getTotalAmount())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }

    public static OrderItemEntity toEntity(OrderItem item, Long orderId) {
        return OrderItemEntity.builder()
                .orderId(orderId)
                .name(item.getName())
                .quantity(item.getQuantity())
                .unitPrice(item.getUnitPrice())
                .subtotal(item.getSubtotal())
                .sku(item.getSku())
                .build();
    }

    public static Order toDomain(OrderEntity entity, List<OrderItemEntity> items) {
        Order order = Order.create(entity.getCustomerEmail(), entity.getCurrency());

        order.setId(entity.getId());
        order.setStatus(entity.getStatus());
        order.setCreatedAt(entity.getCreatedAt());
        order.setUpdatedAt(entity.getUpdatedAt());

        items.forEach(i ->
                order.addItem(
                        i.getId(),
                        i.getSku(),
                        i.getName(),
                        i.getQuantity(),
                        i.getUnitPrice()
                )
        );

        return order;
    }


}