package com.formation.backend.model.dto.out;

import lombok.Data;

@Data
public class UserDtoOut {
    private int id;
    private String username;
    private String password;
    private String dateCreated;
    private RoleDtoOut role;
}
