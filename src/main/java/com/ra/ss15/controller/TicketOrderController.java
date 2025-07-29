package com.ra.ss15.controller;

import com.ra.ss15.model.dto.request.TicketOrderRequest;
import com.ra.ss15.model.dto.response.APIResponse;
import com.ra.ss15.model.entity.TicketOrder;
import com.ra.ss15.model.entity.User;
import com.ra.ss15.service.TicketOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket-orders")
public class TicketOrderController {
    @Autowired
    private TicketOrderService ticketOrderService;

    @PostMapping
    public ResponseEntity<APIResponse<TicketOrder>> createOrder(@RequestBody TicketOrderRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        TicketOrder order = ticketOrderService.placeOrder(user, request.getQuantityTicket(), request.getComboIds());
        return new ResponseEntity<>(new APIResponse<>(true, "Đặt vé thành công!", order, HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @GetMapping
    private ResponseEntity<APIResponse<List<TicketOrder>>> getAllOrders() {
        List<TicketOrder> orders = ticketOrderService.getAllOrders();
        return new ResponseEntity<>(new APIResponse<>(true, "Danh sách đơn đặt vé", orders, HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/my")
    private ResponseEntity<APIResponse<List<TicketOrder>>> getMyOrders() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<TicketOrder> orders = ticketOrderService.getMyOrders(user);
        return new ResponseEntity<>(new APIResponse<>(true, "Lịch sử đặt vé của người dùng", orders, HttpStatus.OK), HttpStatus.OK);
    }
}
