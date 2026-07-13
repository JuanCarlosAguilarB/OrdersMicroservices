package com.example.demo.sales.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ProductSnapshot {
    private Long id;
    private BigDecimal price;
    private Boolean isActive;
}
