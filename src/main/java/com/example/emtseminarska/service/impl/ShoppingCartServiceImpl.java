package com.example.emtseminarska.service.impl;

import com.example.emtseminarska.models.ShoppingCart;
import com.example.emtseminarska.models.exception.CarIsAlreadyInShoppingCartException;
import com.example.emtseminarska.repository.ShoppingCartRepository;
import com.example.emtseminarska.service.CarService;
import com.example.emtseminarska.service.ShoppingCartService;
import com.example.emtseminarska.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final UserService userService;
    private final CarService carService;
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartServiceImpl(UserService userService, CarService carService, ShoppingCartRepository shoppingCartRepository) {
        this.userService = userService;
        this.carService = carService;
        this.shoppingCartRepository = shoppingCartRepository;
    }


    @Override
    public ShoppingCart getShoppingCartForUserId(Long userId) {
        var shoppingCart = this.shoppingCartRepository.findByUserId(userId);
        if(shoppingCart == null) {
            return this.createNewShoppingCart(userId);
        }
        return shoppingCart;
    }

    @Override
    public ShoppingCart createNewShoppingCart(Long userId) {
        var user = this.userService.findById(userId);
        var shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCart = this.shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

    @Override
    @Transactional
    public ShoppingCart addCarToShoppingCart(Long shoppingCartId, Long carId) {
        var shoppingCart = this.shoppingCartRepository.findById(shoppingCartId).orElseThrow();
        var car = this.carService.findById(carId);

        for (var p : shoppingCart.getCars()) {
            if (p.getId().equals(carId)) {
                throw new CarIsAlreadyInShoppingCartException(car.getCarName());
            }
        }
        shoppingCart.getCars().add(car);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    @Transactional
    public void removeCarFromShoppingCart(Long shoppingCartId, Long carId) {
        var shoppingCart = this.shoppingCartRepository.findById(shoppingCartId).orElseThrow();
        shoppingCart.setCars(
                shoppingCart.getCars()
                        .stream()
                        .filter(phone -> !phone.getId().equals(carId))
                        .collect(Collectors.toList())
        );

        this.shoppingCartRepository.save(shoppingCart);
    }
}
