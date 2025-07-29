package com.ra.ss15.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order_id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product_id;
    private Integer quantity;
    private BigDecimal price;
}
