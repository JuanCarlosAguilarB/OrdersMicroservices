package com.example.demo.sales.infrastructure.persistence;

import com.example.demo.sales.domain.Order;
import com.example.demo.sales.domain.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class RDBOrderRepository implements OrderRepository {

    private final R2DBCOrderRepository r2DBCOrderRepository;

    @Override
    public Mono<Order> save(Order order){
        return r2DBCOrderRepository.save(OrdersMapper.fromDomain(order))
                .map(OrdersMapper::toDomain);
    }

}
