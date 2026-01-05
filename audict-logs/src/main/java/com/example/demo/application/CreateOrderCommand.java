package com.example.demo.application;

import com.example.demo.domain.Currency;

import java.util.List;

public record CreateOrderCommand(
        Currency currency,
        String customerEmail,
        List<CreateOrderItemCommand> items
) {
}