package com.ra.ss15.repository;

import com.ra.ss15.model.entity.Role;
import com.ra.ss15.model.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName roleName);
}
