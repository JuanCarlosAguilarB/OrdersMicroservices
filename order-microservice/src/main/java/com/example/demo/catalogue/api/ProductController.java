package com.example.demo.catalogue.api;

import com.example.demo.catalogue.application.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController("/api/v1/products")
public class ProductController {

    private final ProductService service;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<?>> findProductById(@PathVariable Long id){
        return service.findById(id).map(ResponseEntity::ok);
    }
    
}
