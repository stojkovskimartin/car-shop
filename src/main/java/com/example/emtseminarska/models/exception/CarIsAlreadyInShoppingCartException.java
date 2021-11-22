package com.example.emtseminarska.models.exception;

public class CarIsAlreadyInShoppingCartException extends  RuntimeException{
    public CarIsAlreadyInShoppingCartException(String name) {
        super(String.format("Car %s is already in active shopping cart", name));
    }
}
