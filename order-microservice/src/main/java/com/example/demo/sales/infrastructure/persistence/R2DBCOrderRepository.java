package com.example.demo.sales.infrastructure.persistence;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface R2DBCOrderRepository extends R2dbcRepository<OrderEntity, Long> {

}
