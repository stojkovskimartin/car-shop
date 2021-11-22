package com.example.emtseminarska.models.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BrandNotFoundException extends RuntimeException{
    public BrandNotFoundException(Long id) {
        super(String.format("Manufacturer with id %d is not found", id));

    }
}
