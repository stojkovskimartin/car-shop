package com.example.emtseminarska.models.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShoppingCartIsNotActiveException extends RuntimeException{

    public ShoppingCartIsNotActiveException(String firstName) {
        super(String.format("Manufacturer with string %s is not found", firstName));

    }
}
