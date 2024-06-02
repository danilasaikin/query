package com.example.query.service;

import com.example.query.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Transactional(readOnly = true)
    public Double getTotalCost(Long userId) {
        return cartItemRepository.findTotalCostByUserId(userId);
    }

    @Transactional(readOnly = true)
    public Double getTotalCostWithOrders(Long userId) {
        return cartItemRepository.findTotalCostByUserIdWithOrders(userId);
    }

    public void comparePerformance(Long userId) {
        long startOld = System.currentTimeMillis();
        Double totalCostOld = getTotalCostWithOrders(userId);
        long endOld = System.currentTimeMillis();
        System.out.println("Old query total cost: " + totalCostOld);
        System.out.println("Old query execution time: " + (endOld - startOld) + " ms");

        long startNew = System.currentTimeMillis();
        Double totalCostNew = getTotalCost(userId);
        long endNew = System.currentTimeMillis();
        System.out.println("New query total cost: " + totalCostNew);
        System.out.println("New query execution time: " + (endNew - startNew) + " ms");
    }
}
