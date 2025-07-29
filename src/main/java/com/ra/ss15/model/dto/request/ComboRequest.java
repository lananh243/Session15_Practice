package com.ra.ss15.model.dto.request;

import com.ra.ss15.model.entity.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComboRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private String items;
    private Status status;
}
