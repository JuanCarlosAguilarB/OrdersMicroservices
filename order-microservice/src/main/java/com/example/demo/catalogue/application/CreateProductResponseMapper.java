package com.example.demo.catalogue.application;

import com.example.demo.catalogue.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class CreateProductResponseMapper {

    public CreateProductResponse toResponse(Product product) {
        if (product == null) {
            return null;
        }

        return CreateProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .brand(product.getBrand())
                .categoryId(product.getCategoryId())
                .price(product.getPrice())
                .sku(product.getSku())
                .isActive(product.isActive())
                .build();
    }

    public CreateProductResponse from(Product product) {
        return CreateProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .brand(product.getBrand())
                .categoryId(product.getCategoryId())
                .price(product.getPrice())
                .sku(product.getSku())
                .isActive(product.isActive())
                .build();
    }


}

