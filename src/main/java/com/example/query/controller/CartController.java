package com.example.query.controller;

import com.example.query.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/cart/total-cost/{userId}")
    public Double getTotalCost(@PathVariable Long userId) {
        return cartService.getTotalCost(userId);
    }

    @GetMapping("/cart/compare-performance/{userId}")
    public void comparePerformance(@PathVariable Long userId) {
        cartService.comparePerformance(userId);
    }
}
