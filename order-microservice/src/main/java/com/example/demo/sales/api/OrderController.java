package com.example.demo.sales.api;

import com.example.demo.sales.application.OrderService;
import com.example.demo.sales.domain.Order;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/v1/orders")
@AllArgsConstructor
public class OrderController {

    private OrderService service;

    @PostMapping
    public ResponseEntity<?> create (Order order){
        return service.create(order).map(ResponseEntity::ok).block();
    }

}
