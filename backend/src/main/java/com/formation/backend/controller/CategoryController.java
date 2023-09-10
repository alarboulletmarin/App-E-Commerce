package com.formation.backend.controller;

import com.formation.backend.model.dto.out.CategoryDtoOut;
import com.formation.backend.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDtoOut> createCategory(@Valid @RequestBody CategoryDtoOut categoryDtoOut) {
        return ResponseEntity.ok(categoryService.createCategory(categoryDtoOut));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDtoOut>> getCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDtoOut> getCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDtoOut> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDtoOut categoryDtoOut) {
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDtoOut));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
