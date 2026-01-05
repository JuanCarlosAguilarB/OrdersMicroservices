package com.example.demo.infrastructure.persistence;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.example.demo.infrastructure.persistence.OrderMapper.toDomain;
import static com.example.demo.infrastructure.persistence.OrderMapper.toEntity;


@Repository
@RequiredArgsConstructor
public class RDBOrderRepository implements OrderRepository {

    private final DatabaseClient client;
    private final R2dbcEntityTemplate template;

    @Override
    public Mono<Order> save(Order order) {

        OrderEntity orderEntity = toEntity(order);

        return template.insert(OrderEntity.class)
                .using(orderEntity)
                .flatMap(savedOrder -> {

                    List<OrderItemEntity> itemEntities =
                            order.getItems().stream()
                                    .map(item -> toEntity(item, savedOrder.getId()))
                                    .toList();

                    return Flux.fromIterable(itemEntities)
                            .flatMap(template::insert)
                            .collectList()
                            .map(savedItems -> toDomain(savedOrder, savedItems));
                });
    }

}
