package com.ra.ss15.service;

import com.ra.ss15.model.entity.OrderB3;
import com.ra.ss15.model.entity.User;

import java.util.List;

public interface OrderB3Service {
    OrderB3 placeOrder(User user, List<Long> productIds, List<Integer> quantities);
    List<OrderB3> getMyOrders(User user);
    List<OrderB3> getAllOrders();
    OrderB3 updateOrderStatus(Long id, String status);
}
