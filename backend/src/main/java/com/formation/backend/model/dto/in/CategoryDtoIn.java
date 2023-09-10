package com.formation.backend.model.dto.in;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CategoryDtoIn {

    @NotBlank(message = "Code cannot be blank")
    private String code;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 255, message = "Name should be between 3 and 255 characters")
    private String name;
}
