package com.example.demo.catalogue.application;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class CreateProductResponse {

    private final Long id;
    private final String name;
    private final String description;
    private final String brand;
    private final Long categoryId;
    private final BigDecimal price;
    private final String sku;

    private boolean isActive;
}
