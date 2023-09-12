package com.formation.backend.dao;


import com.formation.backend.model.entity.Product;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

@Hidden
public interface ProductRepository extends JpaRepository<Product, Long> {

}
