package com.formation.backend.dao;


import com.formation.backend.model.entity.Role;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for the role entity
 */
@Hidden
public interface RoleRepository extends JpaRepository<Role, Long> {
}