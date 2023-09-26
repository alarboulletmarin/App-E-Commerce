package com.formation.backend.controller;


import com.formation.backend.model.entity.LoginRequest;
import com.formation.backend.model.entity.RegisterRequest;
import com.formation.backend.model.entity.User;
import com.formation.backend.service.UserService;
import com.formation.backend.service.security.AuthService;
import com.formation.backend.service.security.CookieService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * AuthController class is used to handle the authentication requests
 */
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "Operations on authentication")
public class AuthController {
    // Autowire the UserService, AuthService and CookieService beans
    private final UserService userService;
    private final AuthService authService;
    private final CookieService cookieService;

    /**
     * AuthController constructor
     *
     * @param userService   UserService bean
     * @param authService   AuthService bean
     * @param cookieService CookieService bean
     */
    public AuthController(UserService userService, AuthService authService, CookieService cookieService) {
        this.userService = userService;
        this.authService = authService;
        this.cookieService = cookieService;
    }

    /**
     * This method is used to register a new user
     *
     * @param request RegisterRequest object
     * @return ResponseEntity object
     */
    @PostMapping("/register")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "User registered"))
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            User user = userService.createUser(request.getUsername(), request.getPassword(), request.getRoleName());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();  // Ceci imprimera l'exception et sa trace dans la console.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * This method is used to login a user
     *
     * @param request LoginRequest object
     * @return ResponseEntity object
     */
    @PostMapping("/login")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "User logged in"))
    public ResponseEntity<Void> login(@RequestBody LoginRequest request) {
        try {
            String token = authService.authenticate(request.getUsername(), request.getPassword());

            HttpHeaders header = new HttpHeaders();
            header.add("X-App-Session-Token", token);

            return ResponseEntity.ok().headers(header).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /**
     * This method is used to logout a user
     *
     * @param response HttpServletResponse object
     * @return ResponseEntity object
     */
    @PostMapping("/logout")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "User logged out"))
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        // Utile ici car on utilise le header pour le token et donc lors du logout
        // on surpprimera le localstorage
        // On supprime le cookie JWT
        Cookie jwtCookie = cookieService.deleteJwtCookie();
        response.addCookie(jwtCookie);
        return ResponseEntity.ok().build();
    }
}