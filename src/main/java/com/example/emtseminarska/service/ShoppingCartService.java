package com.example.emtseminarska.service;

import com.example.emtseminarska.models.ShoppingCart;


public interface ShoppingCartService {

    ShoppingCart getShoppingCartForUserId(Long userId);

    ShoppingCart createNewShoppingCart(Long userId);

    ShoppingCart addCarToShoppingCart(Long shoppingCartId, Long carId);

    void removeCarFromShoppingCart(Long shoppingCartId, Long carId);
}
