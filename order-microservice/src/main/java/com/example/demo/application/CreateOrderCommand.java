package com.example.demo.application;

import com.example.demo.domain.Currency;
import com.example.demo.domain.OrderItem;

import java.util.List;

public record CreateOrderCommand(
        Currency currency,
        String customerEmail,
        List<CreateOrderItemCommand> items
) {
}