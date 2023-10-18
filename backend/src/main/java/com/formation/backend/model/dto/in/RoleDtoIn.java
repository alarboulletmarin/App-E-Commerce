package com.formation.backend.model.dto.in;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RoleDtoIn {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 255, message = "Name should be between 3 and 255 characters")
    private String name;
}
