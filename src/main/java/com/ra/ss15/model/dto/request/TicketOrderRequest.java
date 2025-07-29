package com.ra.ss15.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketOrderRequest {
    private Integer quantityTicket;
    private List<Long> comboIds;
}
