package com.formation.backend.model.dto.in;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDtoIn {

    @NotNull(message = "Username cannot be null")
    private String username;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 255, message = "Password should be between 8 and 255 characters")
    private String password;

    @NotNull(message = "Role ID cannot be null")
    private Long roleId;

    @NotNull(message = "Email cannot be null")
    private String email;

    @NotNull(message = "First name cannot be null")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    private String lastName;
    private String address;
    private String phone;
    private String city;
    private String zipCode;
}
