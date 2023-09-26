package com.formation.backend.dao;

import com.formation.backend.model.entity.User;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


@Hidden
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}