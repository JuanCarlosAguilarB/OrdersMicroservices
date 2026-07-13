package com.example.demo.catalogue.infrastructure.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface R2DBCProductRepository extends ReactiveCrudRepository<ProductEntity, Long> {

}
