package com.formation.backend.service;


import com.formation.backend.dao.RoleRepository;
import com.formation.backend.exception.NotFoundException;
import com.formation.backend.model.dto.mapper.RoleMapper;
import com.formation.backend.model.dto.out.RoleDtoOut;
import com.formation.backend.model.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * RoleService class is used to handle the role related operations
 */
@Service
public class RoleService {

    private static final String TextNotFound = "Role not found";

    // Autowire the RoleRepository bean
    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    /**
     * RoleService constructor
     *
     * @param roleRepository RoleRepository bean
     * @param roleMapper     RoleMapper bean
     */
    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public List<RoleDtoOut> getRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(roleMapper::entityToDtoOut)
                .collect(Collectors.toList());
    }

    public RoleDtoOut getRole(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NotFoundException(TextNotFound));
        return roleMapper.entityToDtoOut(role);
    }

    public RoleDtoOut createRole(RoleDtoOut roleDtoOut) {
        Role role = roleMapper.dtoInToEntity(roleDtoOut);
        Role savedRole = roleRepository.save(role);
        return roleMapper.entityToDtoOut(savedRole);
    }

    public RoleDtoOut updateRole(Long id, RoleDtoOut roleDtoOut) {
        if (!roleRepository.existsById(id)) {
            throw new NotFoundException(TextNotFound);
        }
        Role role = roleMapper.dtoInToEntity(roleDtoOut);
        role.setId(id);
        Role updatedRole = roleRepository.save(role);
        return roleMapper.entityToDtoOut(updatedRole);
    }

    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new NotFoundException(TextNotFound);
        }
        roleRepository.deleteById(id);
    }


}
