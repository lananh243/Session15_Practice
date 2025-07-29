package com.ra.ss15.controller;

import com.ra.ss15.model.dto.request.OrderB3Request;
import com.ra.ss15.model.dto.response.APIResponse;
import com.ra.ss15.model.entity.OrderB3;
import com.ra.ss15.model.entity.User;
import com.ra.ss15.service.OrderB3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderB3Controller {
    @Autowired
    private OrderB3Service orderB3Service;

    @PostMapping
    public ResponseEntity<APIResponse<OrderB3>> placeOrder(@RequestBody OrderB3Request request) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        OrderB3 order = orderB3Service.placeOrder(currentUser, request.getProductIds(), request.getQuantities());
        return new ResponseEntity<>(new APIResponse<>(true, "Đặt hàng thành công", order, HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @GetMapping("/my")
    public ResponseEntity<APIResponse<List<OrderB3>>> getMyOrder() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(new APIResponse<>(true, "Xem lich sử đặt hàng", orderB3Service.getMyOrders(currentUser), HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<OrderB3>>> getAllOrders() {
        List<OrderB3> orderB3s = orderB3Service.getAllOrders();
        return new ResponseEntity<>(new APIResponse<>(true, "Danh sách đơn hàng",  orderB3s, HttpStatus.OK), HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    private ResponseEntity<APIResponse<OrderB3>> updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        return new ResponseEntity<>(new APIResponse<>(true, "Cập nhật trạng thái",  orderB3Service.updateOrderStatus(id, status), HttpStatus.OK), HttpStatus.OK);
    }
}
