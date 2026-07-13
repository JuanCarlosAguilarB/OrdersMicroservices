package com.example.demo.sales.api;

import com.example.demo.sales.application.OrderCreateDto;
import com.example.demo.sales.application.OrderService;
import com.example.demo.sales.domain.Order;
import com.example.demo.sales.domain.OrderCreateDtoMother;
import com.example.demo.sales.domain.OrderObjectMother;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//  Slice test
@WebFluxTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockitoBean
    OrderService service;

    @Test
    void shouldCreateOrder() {

        OrderCreateDto dto = OrderCreateDtoMother.random();
        Order  mappedOrder = OrderCreateDto.toDomain(dto);

        when(service.create(any()))
                .thenReturn(Mono.just(mappedOrder));

        webTestClient.post()
                .uri("/api/v1/orders")
                .bodyValue(dto)
                .exchange()
                .expectStatus().isOk()
                //.expectBody(Order.class) // TODO fix it, we dont have return a domain entity
               // .isEqualTo(mappedOrder)
        ;
    }
}
