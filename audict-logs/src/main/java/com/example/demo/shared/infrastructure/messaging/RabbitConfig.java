package com.example.demo.shared.infrastructure.messaging;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE = "orders.exchange";
    public static final String QUEUE = "orders.created.audit.queue";
    public static final String DLQ = "orders.created.audit.dlq";
    public static final String ROUTING_KEY = "orders.created";

    @Bean
    DirectExchange ordersExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    Queue auditQueue() {
        return QueueBuilder.durable(QUEUE)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", DLQ)
                .build();
    }

    @Bean
    Queue auditDlq() {
        return QueueBuilder.durable(DLQ).build();
    }

    @Bean
    Binding binding() {
        return BindingBuilder
                .bind(auditQueue())
                .to(ordersExchange())
                .with(ROUTING_KEY);
    }
}
