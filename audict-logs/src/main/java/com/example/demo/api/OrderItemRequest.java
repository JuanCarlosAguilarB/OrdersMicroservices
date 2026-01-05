package com.example.demo.api;


import com.example.demo.application.CreateOrderItemCommand;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemRequest {

    private String sku;
    private String name;
    private Integer quantity;
    private BigDecimal unitPrice;

    public CreateOrderItemCommand toCommand() {
        return new CreateOrderItemCommand(sku, name, quantity, unitPrice);
    }
}
