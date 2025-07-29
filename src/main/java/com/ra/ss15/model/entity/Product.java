package com.ra.ss15.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @Column(name = "product_name", nullable = false, unique = true, length = 100)
    private String productName;
    private String producer;
    private Integer yearMaking;
    private LocalDate expireDate;
    private Integer quantity;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "cate_id")
    private Category category;
}
