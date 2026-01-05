package com.example.demo.domain;

import reactor.core.publisher.Mono;

public interface OrderRepository {
    Mono<Order> save(Order order);
}
