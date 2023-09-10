package com.formation.backend.controller;

import com.formation.backend.model.dto.in.ProductDtoIn;
import com.formation.backend.model.dto.out.ProductDtoOut;
import com.formation.backend.model.dto.out.ProductDtoOutShort;
import com.formation.backend.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDtoOut> createProduct(@Valid @RequestBody ProductDtoIn productDtoIn) {
        return ResponseEntity.ok(productService.createProduct(productDtoIn));
    }

    @GetMapping
    public ResponseEntity<List<ProductDtoOutShort>> getAllProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDtoOut> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductDtoOut> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDtoIn productDtoIn) {
        return ResponseEntity.ok(productService.updateProduct(id, productDtoIn));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
