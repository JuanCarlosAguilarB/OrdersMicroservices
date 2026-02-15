package com.example.demo.catalogue.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Getter
public class Product {


    private final Long id;
    private String name;
    private String description;
    private String brand;
    private Long categoryId;
    private BigDecimal price;
    private String sku;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    private boolean isActive;
    private Integer version;

}
