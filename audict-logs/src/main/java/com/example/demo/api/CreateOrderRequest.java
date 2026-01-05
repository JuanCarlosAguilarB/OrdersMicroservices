package com.example.demo.api;

import com.example.demo.application.CreateOrderCommand;
import com.example.demo.domain.Currency;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    private String customerEmail;
    private String currency;
    private List<OrderItemRequest> items;

    public static CreateOrderCommand toCommand(CreateOrderRequest request) {
        return new CreateOrderCommand(
                Currency.valueOf(request.currency),
                request.customerEmail,
                request.items.stream().map(OrderItemRequest::toCommand).toList()
        );
    }
}