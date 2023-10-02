package com.formation.backend.controller;

import com.formation.backend.model.dto.out.RoleDtoOut;
import com.formation.backend.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/role")
@Tag(name = "Role", description = "Operations on roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    @Operation(summary = "Create a new role", tags = {"Role"})
    @ApiResponses(value = @ApiResponse(responseCode = "201", description = "Role created"))
    public ResponseEntity<RoleDtoOut> createRole(@Valid @RequestBody RoleDtoOut roleDtoOut) {
        return ResponseEntity.ok(roleService.createRole(roleDtoOut));
    }

    @GetMapping
    @Operation(summary = "Retrieve all roles", tags = {"Role"})
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "List of roles"))
    public ResponseEntity<List<RoleDtoOut>> getRoles() {
        return ResponseEntity.ok(roleService.getRoles());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a role by its id", tags = {"Role"})
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Role found"))
    public ResponseEntity<RoleDtoOut> getRole(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.getRole(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a role by its id", tags = {"Role"})
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Role updated"))
    public ResponseEntity<RoleDtoOut> updateRole(@PathVariable Long id, @Valid @RequestBody RoleDtoOut roleDtoOut) {
        return ResponseEntity.ok(roleService.updateRole(id, roleDtoOut));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a role by its id", tags = {"Role"})
    @ApiResponses(value = @ApiResponse(responseCode = "204", description = "Role deleted"))
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
