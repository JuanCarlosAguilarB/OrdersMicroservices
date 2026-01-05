package com.example.demo.domain;

import lombok.Getter;

@Getter
public enum OrderStatus {
    CREATED,
    PENDING,
    PROCESSING,
    COMPLETED,
    PAID, CANCELLED
}