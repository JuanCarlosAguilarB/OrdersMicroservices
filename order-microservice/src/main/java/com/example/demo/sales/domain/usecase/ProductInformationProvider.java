package com.example.demo.sales.domain.usecase;

import com.example.demo.sales.domain.ProductSnapshot;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ProductInformationProvider {
    Flux<ProductSnapshot> findByIds(List<Long> ids);

}

