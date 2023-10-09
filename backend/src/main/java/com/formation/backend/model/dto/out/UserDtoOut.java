package com.formation.backend.model.dto.out;

import lombok.Data;

@Data
public class UserDtoOut {
    private int id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String city;
    private String zipCode;
    private String dateCreated;
    private RoleDtoOut role;
}
