package com.example.demo.sales.application;

import com.example.demo.sales.domain.Order;
import com.example.demo.sales.domain.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    public Mono<Order> create (OrderCreateDto orderDto) {
        return repository.save(OrderCreateDto.toDomain(orderDto));
    }

}
