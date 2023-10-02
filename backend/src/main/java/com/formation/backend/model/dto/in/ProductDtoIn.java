package com.formation.backend.model.dto.in;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ProductDtoIn {

    @NotBlank(message = "Code cannot be blank")
    private String code;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 255, message = "Name should be between 3 and 255 characters")
    private String name;

    @Size(max = 1000, message = "Description should not exceed 1000 characters")
    private String description;

    @Positive(message = "Unit price should be positive")
    private double unitPrice;

    private String imageUrl;

    private boolean active;

    @PositiveOrZero(message = "Units in stock should be zero or positive")
    private int unitsInStock;

    @NotNull(message = "Category ID cannot be null")
    private long categoryId;
}
