package com.example.demo.application;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderRepository;
import com.example.demo.domain.OrderResponse;
import com.example.demo.domain.event.OrderCreatedEvent;
import com.example.demo.shared.domain.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class OrderApplicationService {

    private final OrderRepository orderRepository;
    private final EventPublisher eventPublisher;


    public Mono<OrderResponse> create(CreateOrderCommand  createOrderCommand) {


        Order order = Order.create(createOrderCommand.customerEmail(),
                 createOrderCommand.currency());

        createOrderCommand.items().forEach(item ->
                order.addItem(
                        item.getSku(),
                        item.getName(),
                        item.getQuantity(),
                        item.getUnitPrice()
                )
        );

        return orderRepository.save(order)
                .flatMap(saved ->
                        eventPublisher.publish(
                                new OrderCreatedEvent(
                                        saved.getId(),
                                        saved.getCustomerEmail(),
                                        saved.getTotalAmount(),
                                        saved.getCurrency().name(),
                                        saved.getStatus().name()
                                )
                        ).thenReturn(OrderResponse.fromOrder(saved))
                );
    }

//    public Mono<Order> findById(Long id) {
//        return orderRepository.findById(id);
//    }
//
//    public Flux<Order> findAll(Pageable pageable) {
//        return orderRepository.findAll(pageable);
//    }
}