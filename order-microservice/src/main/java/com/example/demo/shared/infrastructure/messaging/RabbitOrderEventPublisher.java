package com.example.demo.shared.infrastructure.messaging;

import com.example.demo.domain.event.OrderCreatedEvent;
import com.example.demo.shared.domain.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
@RequiredArgsConstructor
public class RabbitOrderEventPublisher
        implements EventPublisher<OrderCreatedEvent> {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public Mono<Void> publish(OrderCreatedEvent event) {
        return Mono.fromRunnable(() -> send(event))
                .subscribeOn(Schedulers.boundedElastic())
                .then();
    }

    private void send(OrderCreatedEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                RabbitConfig.ROUTING_KEY,
                event
        );
    }
}
