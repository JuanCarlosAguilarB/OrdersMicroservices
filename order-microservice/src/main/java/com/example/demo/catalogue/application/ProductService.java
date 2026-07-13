package com.example.demo.catalogue.application;

import com.example.demo.catalogue.domain.ProductRepository;
import com.example.demo.catalogue.domain.execption.ProductNotFound;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository repository;
    private final CreateProductResponseMapper mapper;

    public Mono<CreateProductResponse> findById(Long id){

        if (Objects.isNull(id)){
            log.error("passing a null like id argument");
            return Mono.error(new IllegalArgumentException("Id cannot be null")); // changes for exception's domain
        }

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ProductNotFound("Not product found "  + id)))
                .map(mapper::toResponse);

    }

}
