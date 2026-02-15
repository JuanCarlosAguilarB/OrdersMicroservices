package com.example.demo.catalogue.infrastructure.persistence;

import com.example.demo.catalogue.domain.Product;
import org.springframework.stereotype.Component;


@Component
public class ProductEntityMapper {

    public Product toDomain(ProductEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getBrand(),
                entity.getCategoryId(),
                entity.getPrice(),
                entity.getSku(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.isActive(),
                entity.getVersion()
        );
    }

    public ProductEntity toEntity(Product product) {
        if (product == null) {
            return null;
        }

        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .brand(product.getBrand())
                .categoryId(product.getCategoryId())
                .price(product.getPrice())
                .sku(product.getSku())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .isActive(product.isActive())
                .version(product.getVersion())
                .build();
    }
}
