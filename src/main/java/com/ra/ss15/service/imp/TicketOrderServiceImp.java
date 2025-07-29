package com.ra.ss15.service.imp;

import com.ra.ss15.model.entity.Combo;
import com.ra.ss15.model.entity.TicketOrder;
import com.ra.ss15.model.entity.User;
import com.ra.ss15.repository.ComboRepository;
import com.ra.ss15.repository.TicketOrderRepository;
import com.ra.ss15.service.TicketOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketOrderServiceImp implements TicketOrderService {
    @Autowired
    private TicketOrderRepository ticketOrderRepository;
    @Autowired
    private ComboRepository comboRepository;

    @Override
    public TicketOrder placeOrder(User user, Integer quantityTicket, List<Long> comboIds) {
        List<Combo> combos = comboRepository.findAllById(comboIds);

        BigDecimal total = BigDecimal.valueOf(quantityTicket * 50000);
        for (Combo combo : combos) {
            total = total.add(combo.getPrice());
        }

        TicketOrder ticketOrder = TicketOrder.builder()
                .user(user)
                .quantityTicket(quantityTicket)
                .combos(combos)
                .totalMoney(total)
                .createdAt(LocalDateTime.now())
                .build();
        return ticketOrderRepository.save(ticketOrder);
    }

    @Override
    public List<TicketOrder> getMyOrders(User user) {
        return ticketOrderRepository.findByUser(user);
    }

    @Override
    public List<TicketOrder> getAllOrders() {
        return ticketOrderRepository.findAll();
    }
}
