package com.formation.backend.model.dto.mapper;

import com.formation.backend.model.dto.out.RoleDtoOut;
import com.formation.backend.model.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDtoOut entityToDtoOut(Role role);

    Role dtoInToEntity(RoleDtoOut roleDtoOut);

}
