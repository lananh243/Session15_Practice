package com.ra.ss15.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String productName;
    private String producer;
    private Integer yearMaking;
    private LocalDate expireDate;
    private Integer quantity;
    private BigDecimal price;
    private Long cateId;
}
