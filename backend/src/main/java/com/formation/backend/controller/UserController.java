package com.formation.backend.controller;

import com.formation.backend.model.dto.in.UserDtoIn;
import com.formation.backend.model.dto.out.UserDtoOut;
import com.formation.backend.service.UserService;
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
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Operations on users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Create a new user
     *
     * @param userDtoIn the user to create
     * @return the created user
     */
    @PostMapping
    @Operation(summary = "Create a new user", tags = {"Users"})
    @ApiResponses(value = @ApiResponse(responseCode = "201", description = "User created", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserDtoIn.class)))}))
    public ResponseEntity<UserDtoOut> createUser(@Valid @RequestBody UserDtoIn userDtoIn) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDtoIn));
    }

    /**
     * Retrieve the current user
     *
     * @param token the token of the current user
     * @return the current user
     */
    //    @PreAuthorize("")
    @GetMapping("/current")
    @Operation(summary = "Retrieve the current user", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Current user", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserDtoOut.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<UserDtoOut> getCurrentUser(@RequestHeader("Authorization") String token) {
        UserDtoOut userDto = userService.getCurrentUser(token);
        return ResponseEntity.ok(userDto);
    }

    /**
     * Retrieve a user by its id
     *
     * @param id the id of the user to retrieve
     * @return the user
     */
    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a user by its id", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserDtoOut.class))}),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public ResponseEntity<UserDtoOut> getUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(id));
    }

    /**
     * Retrieve all users
     *
     * @return the list of users
     */
    @GetMapping
    @Operation(summary = "Retrieve all users", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of users", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserDtoOut.class)))})
    })
    public ResponseEntity<List<UserDtoOut>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }

    /**
     * Update a user by its id
     *
     * @param id        the id of the user to update
     * @param userDtoIn the user to update
     * @return the updated user
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update a user by its id", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserDtoOut.class))}),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public ResponseEntity<UserDtoOut> updateUser(@PathVariable Long id, @Valid @RequestBody UserDtoIn userDtoIn) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, userDtoIn));
    }

    /**
     * Delete a user by its id
     *
     * @param id the id of the user to delete
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user by its id", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserDtoOut.class))}),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
