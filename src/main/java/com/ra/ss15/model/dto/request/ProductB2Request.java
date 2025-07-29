package com.ra.ss15.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductB2Request {
    private String name;
    private String description;
    private BigDecimal price;
    private String size;
    private String toppings;
}
