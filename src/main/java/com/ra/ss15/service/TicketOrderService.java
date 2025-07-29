package com.ra.ss15.service;

import com.ra.ss15.model.entity.TicketOrder;
import com.ra.ss15.model.entity.User;

import java.util.List;

public interface TicketOrderService {
    TicketOrder placeOrder(User user, Integer quantityTicket, List<Long> comboIds);
    List<TicketOrder> getMyOrders(User user);
    List<TicketOrder> getAllOrders();
}
