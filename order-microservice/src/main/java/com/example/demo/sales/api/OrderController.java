package com.example.demo.sales.api;

import com.example.demo.sales.application.OrderService;
import com.example.demo.sales.domain.Order;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {

    private OrderService service;

    @PostMapping
    public Mono<ResponseEntity<Order>> create (@RequestBody Order order){
        return service.create(order).map(ResponseEntity::ok);
    }

}
