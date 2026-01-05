package com.example.demo.shared.infrastructure.messaging;

import com.example.demo.domain.event.OrderCreatedEvent;
import com.example.demo.shared.domain.EventPublisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class RabbitOrderEventPublisher
        implements EventPublisher<OrderCreatedEvent> {

    @Override
    public Mono<Void> publish(OrderCreatedEvent event) {
        return Mono.empty();
    }
}
