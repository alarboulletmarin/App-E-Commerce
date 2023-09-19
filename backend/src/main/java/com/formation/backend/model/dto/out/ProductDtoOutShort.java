package com.formation.backend.model.dto.out;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDtoOutShort {

    private int id;
    private String code;
    private String name;
    private String description;
    private double unitPrice;
    private String imageUrl;
    private CategoryDtoOut category;
}
