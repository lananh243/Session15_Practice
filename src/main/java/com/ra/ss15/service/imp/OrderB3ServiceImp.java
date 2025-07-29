package com.ra.ss15.service.imp;

import com.ra.ss15.model.entity.*;
import com.ra.ss15.repository.OrderB3Repository;
import com.ra.ss15.repository.OrderItemRepository;
import com.ra.ss15.repository.ProductB2Repository;
import com.ra.ss15.repository.ProductRepository;
import com.ra.ss15.service.OrderB3Service;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class OrderB3ServiceImp implements OrderB3Service {
    @Autowired
    private OrderB3Repository orderB3Repository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductB2Repository productB2Repository;

    @Override
    public OrderB3 placeOrder(User user, List<Long> productIds, List<Integer> quantities) {
        List<OrderItem> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (int i = 0; i < productIds.size(); i++) {
            ProductB2 product = productB2Repository.findById(productIds.get(i))
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));
            int quantity = quantities.get(i);
            BigDecimal price = product.getPrice();

            OrderItem item = OrderItem.builder()
                    .product(product)
                    .quantity(quantity)
                    .priceBuy(price)
                    .build();

            total = total.add(price.multiply(BigDecimal.valueOf(quantity)));
            items.add(item);
        }

        OrderB3 order = OrderB3.builder()
                .user(user)
                .createdDate(LocalDateTime.now())
                .status(OrderStatus.PENDING)
                .totalMoney(total)
                .build();

        OrderB3 savedOrder = orderB3Repository.save(order);
        for (OrderItem item : items) {
            item.setOrder(savedOrder);
            orderItemRepository.save(item);
        }

        savedOrder.setOrderItems(items);
        return savedOrder;
    }

    @Override
    public List<OrderB3> getMyOrders(User user) {
        return orderB3Repository.findByUser(user);
    }

    @Override
    public List<OrderB3> getAllOrders() {
        return orderB3Repository.findAll();
    }

    @Override
    public OrderB3 updateOrderStatus(Long id, String status) {
        OrderB3 order = orderB3Repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        order.setStatus(OrderStatus.valueOf(status.toUpperCase()));
        return orderB3Repository.save(order);
    }
}
