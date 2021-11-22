package com.example.emtseminarska.models.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class CarOutOfStockException extends RuntimeException{
    public CarOutOfStockException(String name) {
        super(String.format("Product with id %s is out of stock!", name));
    }
}
