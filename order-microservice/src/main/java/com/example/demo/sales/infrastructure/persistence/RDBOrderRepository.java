package com.example.demo.sales.infrastructure.persistence;

import com.example.demo.sales.domain.Order;
import com.example.demo.sales.domain.OrderRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class RDBOrderRepository implements OrderRepository {

    @Override
    public Mono<Order> save(Order order){
        return Mono.empty();
    }

}
