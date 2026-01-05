package com.example.demo.persistence;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoOrderAuditRepository extends ReactiveMongoRepository<OrderAudit, String> {
}
