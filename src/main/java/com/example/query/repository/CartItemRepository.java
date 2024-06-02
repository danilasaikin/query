package com.example.query.repository;

import com.example.query.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("SELECT SUM(p.price * ci.quantity) " +
            "FROM CartItem ci " +
            "JOIN Product p " +
            "ON ci.productId = p.id " +
            "WHERE ci.userId = :userId")
    Double findTotalCostByUserId(@Param("userId") Long userId);

    @Query("SELECT SUM(p.price * ci.quantity) " +
            "FROM CartItem ci " +
            "JOIN Product p " +
            "ON ci.productId = p.id " +
            "JOIN UserOrder o ON o.userId = ci.userId " +
            "WHERE o.status = 'active' " +
            "AND o.userId = :userId")
    Double findTotalCostByUserIdWithOrders(@Param("userId") Long userId);
}
