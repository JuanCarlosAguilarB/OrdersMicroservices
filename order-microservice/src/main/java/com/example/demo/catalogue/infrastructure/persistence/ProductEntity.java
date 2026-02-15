package com.example.demo.catalogue.infrastructure.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.OffsetDateTime;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(value = "product", schema = "catalogue")
public class ProductEntity {

    @Id
    private Long id;

    private String name;
    private String description;
    private String brand;

    private Long categoryId;

    private BigDecimal price;
    private String sku;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    private boolean isActive;

    @Version
    private Integer version;


}
