package com.example.demo.domain;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Builder
public class Order {

    private Long id;
    private String customerEmail;
    private OrderStatus status;
    private BigDecimal totalAmount;

    private Currency currency;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder.Default
    private List<OrderItem> items = new ArrayList<>();

    public static Order create( String customerEmail, Currency currency) {
        Order order = Order.builder()
                .customerEmail(customerEmail)
                .currency(currency)
                .status(OrderStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return order;
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addItem(String sku, String name, int quantity, BigDecimal unitPrice) {
        items.add(new OrderItem(name, quantity, unitPrice, sku, this.id));
        recalculateTotal();
        touch();
    }

    public void addItem(Long id, String sku, String name, int quantity, BigDecimal unitPrice) {
        items.add(new OrderItem(id, name, quantity, unitPrice, sku, this.id));
        recalculateTotal();
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
        recalculateTotal();
        touch();
    }

    public void markAsPaid() {
        if (items.isEmpty()) {
            throw new IllegalStateException("Cannot pay an order without items");
        }
        this.status = OrderStatus.PAID;
        touch();
    }

    public void cancel() {
        if (status == OrderStatus.PAID) {
            throw new IllegalStateException("Paid orders cannot be cancelled");
        }
        this.status = OrderStatus.CANCELLED;
        touch();
    }

    private void recalculateTotal() {
        this.totalAmount = items.stream()
                .map(OrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void touch() {
        this.updatedAt = LocalDateTime.now();
    }

}