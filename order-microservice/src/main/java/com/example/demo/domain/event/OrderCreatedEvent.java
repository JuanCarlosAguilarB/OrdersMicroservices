package com.example.demo.domain.event;


import com.example.demo.application.OrderSnapshot;
import com.example.demo.shared.domain.DomainEvent;
import lombok.Data;

import java.math.BigDecimal;

@Data
public final class OrderCreatedEvent extends DomainEvent{

    private final OrderSnapshot order;

    public OrderCreatedEvent(Long id, String customerEmail, BigDecimal totalAmount, String currency, String status) {
        this.order = new OrderSnapshot(id, customerEmail, totalAmount, currency, status);
    }

    @Override
    public String eventName() {
        return "OrderCreate";
    }
}