package com.example.demo.sales.api;

import com.example.demo.sales.application.OrderService;
import com.example.demo.sales.domain.Order;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {

    private OrderService service;

    @PostMapping
    public Mono<ResponseEntity<Order>> create (
            @RequestBody CreateOrderRequest request,
            ServerHttpRequest httpRequest){

        InetAddress ipAddress = Objects.requireNonNull(httpRequest.getRemoteAddress())
                .getAddress();

        return service.create(CreateOrderRequestMapper.toDto(request, ipAddress, "API")).map(ResponseEntity::ok);
    }

}
