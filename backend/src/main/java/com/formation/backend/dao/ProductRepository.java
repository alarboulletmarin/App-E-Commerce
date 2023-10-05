package com.formation.backend.dao;


import com.formation.backend.model.entity.Product;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repository for the product entity
 */
@Hidden
public interface ProductRepository extends JpaRepository<Product, Long> {

}
