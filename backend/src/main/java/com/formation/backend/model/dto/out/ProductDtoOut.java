package com.formation.backend.model.dto.out;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDtoOut {

    private int id;
    private String code;
    private String name;
    private String description;
    private double unitPrice;
    private String imageUrl;
    private boolean active;
    private int unitsInStock;
    private String dateCreated;
    private String lastUpdated;
    private CategoryDtoOut category;
}
