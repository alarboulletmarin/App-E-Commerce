package com.formation.backend.controller;

import com.formation.backend.model.dto.in.ProductDtoIn;
import com.formation.backend.model.dto.out.ProductDtoOut;
import com.formation.backend.model.dto.out.ProductDtoOutShort;
import com.formation.backend.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Operations on products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @Operation(summary = "Create a new product", tags = {"Products"})
    @ApiResponses(value = @ApiResponse(responseCode = "201", description = "Product created", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDtoIn.class)))}))
    public ResponseEntity<ProductDtoOut> createProduct(@Valid @RequestBody ProductDtoIn productDtoIn) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productDtoIn));

    }

    @GetMapping
    @Operation(summary = "Retrieve all products", tags = {"Products"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of products", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDtoOutShort.class)))})
    })
    public ResponseEntity<List<ProductDtoOutShort>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a product by its id", tags = {"Products"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProductDtoOut.class))}),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content)
    })
    public ResponseEntity<ProductDtoOut> getProduct(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProduct(id));
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update a product by its id", tags = {"Products"})
    @ApiResponse(responseCode = "200", description = "Product updated", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProductDtoOut.class))})
    public ResponseEntity<ProductDtoOut> updateProduct(@NotNull @PathVariable Long id, @Valid @RequestBody ProductDtoIn productDtoIn) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(id, productDtoIn));

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product by its id", tags = {"Products"})
    @ApiResponse(responseCode = "204", description = "Product deleted")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
