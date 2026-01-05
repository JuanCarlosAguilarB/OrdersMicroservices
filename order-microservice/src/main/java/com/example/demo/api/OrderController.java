package com.example.demo.api;

import com.example.demo.application.OrderApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderApplicationService orderApplicationService;

    @PostMapping
    public Mono<ResponseEntity<?>> create(
            @Valid @RequestBody Mono<CreateOrderRequest> request
    ) {
        return request
                .map(CreateOrderRequest::toCommand)
                .flatMap(orderApplicationService::create)
                .map(ResponseEntity::ok);
    }

//    @GetMapping("/{id}")
//    public Mono<OrderResponse> getById(@PathVariable Long id) {
//        return queryService.findById(id)
//                .map(OrderResponse::from);
//    }
//
//    @GetMapping
//    public Flux<OrderResponse> list(Pageable pageable) {
//        return queryService.findAll(pageable)
//                .map(OrderResponse::from);
//    }
}