package com.example.demo.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Data
public class OrderItem {

    private Long id;
    private Long orderId;
    private String sku;
    private String name;
    private int quantity;
    private BigDecimal unitPrice;

    public OrderItem(String name, int quantity, BigDecimal unitPrice, String sku, Long orderId) {
        this.name = Objects.requireNonNull(name);
        this.unitPrice = Objects.requireNonNull(unitPrice);
        this.sku = Objects.requireNonNull(sku);

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        this.quantity = quantity;
    }

    public OrderItem(Long id, String name, int quantity, BigDecimal unitPrice, String sku, Long orderId) {
        this.name = Objects.requireNonNull(name);
        this.id = Objects.requireNonNull(id);
        this.unitPrice = Objects.requireNonNull(unitPrice);
        this.sku = Objects.requireNonNull(sku);

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        this.quantity = quantity;
    }


    public BigDecimal getSubtotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public void changeQuantity(int newQuantity) {
        if (newQuantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        this.quantity = newQuantity;
    }

}