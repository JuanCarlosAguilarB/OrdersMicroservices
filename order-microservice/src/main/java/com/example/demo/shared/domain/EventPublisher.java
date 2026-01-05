package com.example.demo.shared.domain;

import reactor.core.publisher.Mono;

public interface EventPublisher<T> {

    Mono<Void> publish(T event);

}