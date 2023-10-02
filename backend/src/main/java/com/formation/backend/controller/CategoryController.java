package com.formation.backend.controller;

import com.formation.backend.model.dto.out.CategoryDtoOut;
import com.formation.backend.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Categories", description = "Operations on categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @Operation(summary = "Create a new category", tags = {"Categories"})
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Category created"))
    public ResponseEntity<CategoryDtoOut> createCategory(@Valid @RequestBody CategoryDtoOut categoryDtoOut) {
        return ResponseEntity.ok(categoryService.createCategory(categoryDtoOut));
    }

    @GetMapping
    @Operation(summary = "Retrieve all categories", tags = {"Categories"})
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "List of categories"))
    public ResponseEntity<List<CategoryDtoOut>> getCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a category by its id", tags = {"Categories"})
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Category found"))
    public ResponseEntity<CategoryDtoOut> getCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a category by its id", tags = {"Categories"})
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Category updated"))
    public ResponseEntity<CategoryDtoOut> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDtoOut categoryDtoOut) {
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDtoOut));
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category by its id", tags = {"Categories"})
    @ApiResponses(value = @ApiResponse(responseCode = "204", description = "Category deleted"))
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
