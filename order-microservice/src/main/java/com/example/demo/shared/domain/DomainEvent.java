package com.example.demo.shared.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public abstract class DomainEvent {

    private final UUID eventId;
    private final LocalDateTime occurredAt;

    protected DomainEvent() {
        this.eventId = UUID.randomUUID();
        this.occurredAt = LocalDateTime.now();
    }

    public abstract String eventName();
}
