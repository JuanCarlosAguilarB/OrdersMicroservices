package com.example.demo.catalogue.domain.execption;

public class ProductNotFound extends RuntimeException{
    public ProductNotFound(String message){
        super(message);
    }
}
