package com.formation.backend.controller;

import com.formation.backend.model.dto.in.RoleDtoIn;
import com.formation.backend.model.dto.out.RoleDtoOut;
import com.formation.backend.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
@RequestMapping("/api/roles")
@Tag(name = "Roles", description = "Operations on roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * Create a new role
     *
     * @param roleDtoIn the role to create
     * @return the created role
     */
    @PostMapping
    @Operation(summary = "Create a new role", tags = {"Roles"})
    @ApiResponses(value = @ApiResponse(responseCode = "201", description = "Role created", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RoleDtoIn.class)))}))
    public ResponseEntity<RoleDtoOut> createRole(@Valid @RequestBody RoleDtoIn roleDtoIn) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(roleDtoIn));
    }

    /**
     * Retrieve all roles
     *
     * @return the list of roles
     */
    @GetMapping
    @Operation(summary = "Retrieve all roles", tags = {"Roles"})
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "List of roles"))
    public ResponseEntity<List<RoleDtoOut>> getRoles() {
        return ResponseEntity.ok(roleService.getRoles());
    }

    /**
     * Retrieve a role by its id
     *
     * @param id the id of the role to retrieve
     * @return the role
     */
    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a role by its id", tags = {"Roles"})
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Role found"))
    public ResponseEntity<RoleDtoOut> getRole(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.getRole(id));
    }

    /**
     * Update a role by its id
     *
     * @param id        the id of the role to update
     * @param roleDtoIn the role to update
     * @return the updated role
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update a role by its id", tags = {"Roles"})
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Role updated"))
    public ResponseEntity<RoleDtoOut> updateRole(@PathVariable Long id, @Valid @RequestBody RoleDtoIn roleDtoIn) {
        return ResponseEntity.ok(roleService.updateRole(id, roleDtoIn));
    }

    /**
     * Delete a role by its id
     *
     * @param id the id of the role to delete
     * @return nothing
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a role by its id", tags = {"Roles"})
    @ApiResponses(value = @ApiResponse(responseCode = "204", description = "Role deleted"))
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
