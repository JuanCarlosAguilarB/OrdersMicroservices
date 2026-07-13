package com.example.demo.sales.infrastructure.providers.product;

import com.example.demo.sales.domain.ProductSnapshot;
import com.example.demo.sales.domain.usecase.ProductInformationProvider;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductInformationWebClientAdapter implements ProductInformationProvider {


    private final WebClient webClient;

    public ProductInformationWebClientAdapter(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("/api/v1/products")
                .build();
    }



    @Override
    public Flux<ProductSnapshot> findByIds(List<Long> ids) {

        String idsParam = ids.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("ids", idsParam)
                        .build())
                .retrieve()
                .bodyToFlux(ProductSnapshot.class)
                .map(product -> ProductSnapshot.builder()
                        .id(product.getId())
                        .price(product.getPrice())
                        .isActive(product.getIsActive())
                        .build());
    }
}
