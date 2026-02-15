package com.example.demo.catalogue.domain;

import reactor.core.publisher.Mono;

public interface ProductRepository {
    Mono<Product> findById(Long id);
}
