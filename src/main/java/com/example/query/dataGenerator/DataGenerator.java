package com.example.query.dataGenerator;

import com.example.query.entity.CartItem;
import com.example.query.entity.UserOrder;
import com.example.query.entity.Product;
import com.example.query.repository.CartItemRepository;
import com.example.query.repository.UserOrderRepository;
import com.example.query.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Random;

@Component
public class DataGenerator implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserOrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        Random random = new Random();

        // Генерация продуктов
        for (long i = 1; i <= 10000; i++) {
            Product product = new Product();
            product.setPrice(BigDecimal.valueOf(random.nextDouble() * 100));
            productRepository.save(product);
        }

        // Генерация товаров в корзине для пользователя с userId = 1
        for (long i = 1; i <= 10000; i++) {
            CartItem cartItem = new CartItem();
            cartItem.setUserId(1L);
            cartItem.setProductId(i);
            cartItem.setQuantity(random.nextInt(10) + 1);
            cartItemRepository.save(cartItem);
        }

        // Генерация активного заказа для пользователя с userId = 1
        UserOrder order = new UserOrder();
        order.setUserId(1L);
        order.setStatus("active");
        orderRepository.save(order);
    }
}
