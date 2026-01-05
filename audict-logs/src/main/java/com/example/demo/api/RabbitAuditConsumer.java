package com.example.demo.api;


import com.example.demo.application.AuditService;
import com.example.demo.domain.event.OrderCreatedEvent;
import com.example.demo.shared.infrastructure.messaging.RabbitConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.security.MessageDigest;

@Component
public class RabbitAuditConsumer {

    private final AuditService auditService;
    private final ObjectMapper mapper;

    public RabbitAuditConsumer(AuditService auditService, ObjectMapper mapper) {
        this.auditService = auditService;
        this.mapper = mapper;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public Mono<Void> consume(OrderCreatedEvent event) {

        String payloadHash = hash(event);

        return auditService.register(event, payloadHash)
                .onErrorResume(ex -> {
                    return Mono.empty();
                });
    }

    private String hash(Object event) {
        try {
            byte[] json = mapper.writeValueAsBytes(event);
            return MessageDigest.getInstance("SHA-256")
                    .digest(json)
                    .toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}