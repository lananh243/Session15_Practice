package com.ra.ss15.repository;

import com.ra.ss15.model.entity.TicketOrder;
import com.ra.ss15.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketOrderRepository extends JpaRepository<TicketOrder,Long> {
    List<TicketOrder> findByUser(User user);
}
