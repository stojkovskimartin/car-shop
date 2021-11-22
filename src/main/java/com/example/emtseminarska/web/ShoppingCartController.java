package com.example.emtseminarska.web;

import com.example.emtseminarska.service.AuthService;
import com.example.emtseminarska.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/shoppingCarts")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final AuthService authService;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  AuthService authService) {
        this.shoppingCartService = shoppingCartService;
        this.authService = authService;
    }

    @GetMapping("/list")
    public String listAllCarsInShoppingCart(Model model) {
        var userId = this.authService.getCurrentUserId();
        var shoppingCart = this.shoppingCartService.getShoppingCartForUserId(userId);
        var CarsInShoppingCart = shoppingCart.getCars();
        model.addAttribute("CarsInShoppingCart", CarsInShoppingCart);
        return "cart";
    }

    @PostMapping("/add/{carId}")
    public String addCarToShoppingCart(@PathVariable Long carId, RedirectAttributes redirectAttributes) {
        var userId = this.authService.getCurrentUserId();
        var shoppingCart = this.shoppingCartService.getShoppingCartForUserId(userId);
        for (var p : shoppingCart.getCars()) {
            if (p.getId().equals(carId)) {
                redirectAttributes.addAttribute("error", "PhoneIsAlreadyInShoppingCart");
                return "redirect:/cars";
            }
        }
        this.shoppingCartService.addCarToShoppingCart(shoppingCart.getId(), carId);
        return "redirect:/cars";
    }

    @PostMapping("/remove/{carId}")
    public String removeCarFromShoppingCart(@PathVariable Long carId) {
        var userId = this.authService.getCurrentUserId();
        var shoppingCart = this.shoppingCartService.getShoppingCartForUserId(userId);

        this.shoppingCartService.removeCarFromShoppingCart(shoppingCart.getId(), carId);

        return "redirect:/shoppingCarts/list";
    }
}

