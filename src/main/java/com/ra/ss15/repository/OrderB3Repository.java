package com.ra.ss15.repository;

import com.ra.ss15.model.entity.OrderB3;
import com.ra.ss15.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderB3Repository extends JpaRepository<OrderB3, Long> {
    List<OrderB3> findByUser(User user);
}
