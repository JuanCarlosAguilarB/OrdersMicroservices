package com.example.demo.application;

import com.example.demo.domain.OrderAuditRepository;
import com.example.demo.domain.event.OrderCreatedEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AuditService {

    private final OrderAuditRepository repository;

    public AuditService(OrderAuditRepository repository) {
        this.repository = repository;
    }

    public Mono<Void> register(OrderCreatedEvent event, String payloadHash) {
        return repository.existsByPayloadHash(payloadHash)
                .flatMap(exists -> {
                    if (exists) return Mono.empty();
                    return repository.save(
                            OrderAudit.from(event, payloadHash)
                    ).then();
                });
    }
}