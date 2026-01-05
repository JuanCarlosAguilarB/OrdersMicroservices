package com.example.demo.application;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class CreateOrderItemCommand {

    private String sku;
    private String name;
    private Integer quantity;
    private BigDecimal unitPrice;

}
