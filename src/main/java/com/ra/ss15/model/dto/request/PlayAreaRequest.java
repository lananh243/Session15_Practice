package com.ra.ss15.model.dto.request;

import com.ra.ss15.model.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayAreaRequest {
    private String name;
    private String description;
    private Integer maxCapacity;
    private Status status;
}
