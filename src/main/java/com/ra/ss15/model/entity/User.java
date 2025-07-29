package com.ra.ss15.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "username", nullable = false, length = 100, unique = true)
    private String userName;
    @Column(nullable = false, length = 50)
    private String password;
    @Column(length = 150, nullable = false, name = "fullname")
    private String fullName;
    @Column(length = 100, nullable = false, unique = true)
    private String email;
    @Column(length = 50, nullable = false, unique = true)
    private String phone;
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
}
