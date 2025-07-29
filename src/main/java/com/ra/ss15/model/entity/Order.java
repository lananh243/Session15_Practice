package com.ra.ss15.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;
    @Column(name = "full_name", nullable = false, unique = true, length = 100)
    private String fullName;
    @Column(length = 100)
    private String address;
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    @Column(length = 100, nullable = false, unique = true)
    private String phone;
}
