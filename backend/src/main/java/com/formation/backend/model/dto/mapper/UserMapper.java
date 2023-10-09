package com.formation.backend.model.dto.mapper;

import com.formation.backend.dao.RoleRepository;
import com.formation.backend.exception.NotFoundException;
import com.formation.backend.model.dto.in.UserDtoIn;
import com.formation.backend.model.dto.out.RoleDtoOut;
import com.formation.backend.model.dto.out.UserDtoOut;
import com.formation.backend.model.entity.Role;
import com.formation.backend.model.entity.User;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "role", target = "role")
    UserDtoOut entityToDtoOut(User user);

    RoleDtoOut roleToRoleDtoOut(Role role);

    User dtoInToEntity(UserDtoIn userDtoIn, @Context RoleRepository roleRepository);

    default Role findRole(Long roleId, RoleRepository roleRepository) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new NotFoundException("Role not found"));
    }
}
