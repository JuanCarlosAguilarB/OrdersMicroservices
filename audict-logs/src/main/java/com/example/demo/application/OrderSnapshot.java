package com.example.demo.application;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public final class OrderSnapshot {

    private final Long id;
    private final String customerEmail;
    private final BigDecimal totalAmount;
    private final String currency;
    private final String status;

}
