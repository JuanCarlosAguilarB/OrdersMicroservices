package com.example.demo.catalogue.infrastructure.persistence;

import com.example.demo.catalogue.domain.Product;
import com.example.demo.catalogue.domain.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Repository
public class RDBProductRepository implements ProductRepository {

    private final R2DBCProductRepository r2DBCProductRepository;
    private final ProductEntityMapper mapper;

    @Override
    public Mono<Product> findById(Long id) {
        return r2DBCProductRepository
                .findById(id)
                .map(mapper::toDomain);
    }
}