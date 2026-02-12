package com.example.demo.sales.api;

import com.example.demo.sales.application.OrderCreateDto;
import com.example.demo.sales.domain.Order;
import com.example.demo.sales.domain.OrderCreateDtoMother;
import com.example.demo.sales.domain.OrderObjectMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class OrderE2ETest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void shouldCreateOrderE2E() {
        OrderCreateDto order = OrderCreateDtoMother.random();


        webTestClient.post()
                .uri("/api/v1/orders")
                .bodyValue(order)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
//                .jsonPath("$.name").isEqualTo("order-e2e")
        ;
    }
}

