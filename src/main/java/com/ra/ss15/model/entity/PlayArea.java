package com.ra.ss15.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "play_area_id")
    private Long id;
    private String name;
    private String description;
    private Integer maxCapacity;
    @Enumerated(EnumType.STRING)
    private Status status;
}
