package com.formation.backend.controller;

import com.formation.backend.model.dto.in.CategoryDtoIn;
import com.formation.backend.model.dto.out.CategoryDtoOut;
import com.formation.backend.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
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

    /**
     * Create a new category
     *
     * @param categoryDtoIn CategoryDtoOut object
     * @return ResponseEntity object
     */
    @PostMapping
    @Operation(summary = "Create a new category", tags = {"Categories"})
    @ApiResponses(value = @ApiResponse(responseCode = "201", description = "Category created"))
    public ResponseEntity<CategoryDtoOut> createCategory(@Valid @RequestBody CategoryDtoIn categoryDtoIn) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryDtoIn));
    }

    /**
     * Retrieve all categories
     *
     * @return ResponseEntity object
     */
    @GetMapping
    @Operation(summary = "Retrieve all categories", tags = {"Categories"})
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "List of categories"))
    public ResponseEntity<List<CategoryDtoOut>> getCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategories());
    }

    /**
     * Retrieve a category by its id
     *
     * @param id Category id
     * @return ResponseEntity object
     */
    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a category by its id", tags = {"Categories"})
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Category found"))
    public ResponseEntity<CategoryDtoOut> getCategory(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategory(id));
    }

    /**
     * Update a category by its id
     *
     * @param id            Category id
     * @param categoryDtoIn CategoryDtoOut object
     * @return ResponseEntity object
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update a category by its id", tags = {"Categories"})
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Category updated"))
    public ResponseEntity<CategoryDtoOut> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDtoIn categoryDtoIn) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategory(id, categoryDtoIn));
    }

    /**
     * Delete a category by its id
     *
     * @param id Category id
     * @return ResponseEntity object
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category by its id", tags = {"Categories"})
    @ApiResponses(value = @ApiResponse(responseCode = "204", description = "Category deleted"))
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
