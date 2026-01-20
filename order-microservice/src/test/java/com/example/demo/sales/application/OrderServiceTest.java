package com.example.demo.sales.application;

import com.example.demo.sales.domain.Order;
import com.example.demo.sales.domain.OrderObjectMother;
import com.example.demo.sales.domain.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

/***
 * remember
 * usamos step verifier en vez de assertions because it is for spring mvc and it valid values and Monos or FLux are not values, are promise of values.
 */

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    OrderRepository repository;

    @InjectMocks
    OrderService service;

    @Test
    void shouldCreateOrder() {
        Order order = OrderObjectMother.getRandomOrder();

        when(repository.save(order)).thenReturn(Mono.just(order));

        StepVerifier.create(service.create(order))
                .expectNext(order)
                .verifyComplete();
    }
}
