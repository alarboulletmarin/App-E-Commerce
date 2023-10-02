package com.formation.backend.service;

import com.formation.backend.config.security.JwtTokenProvider;
import com.formation.backend.dao.RoleRepository;
import com.formation.backend.dao.UserRepository;
import com.formation.backend.exception.NotFoundException;
import com.formation.backend.model.dto.in.UserDtoIn;
import com.formation.backend.model.dto.mapper.UserMapper;
import com.formation.backend.model.dto.out.UserDtoOut;
import com.formation.backend.model.entity.Role;
import com.formation.backend.model.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserService class is used to handle the user related operations
 */
@Service
public class UserService {

    private static final String ROLE_USER = "ROLE_USER";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    private static final String TextNotFound = "User not found";

    // Autowire the PasswordEncoder, UserRepository, RoleRepository and JwtTokenProvider beans
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    /**
     * UserService constructor
     *
     * @param passwordEncoder  PasswordEncoder bean
     * @param userRepository   UserRepository bean
     * @param roleRepository   RoleRepository bean
     * @param jwtTokenProvider JwtTokenProvider bean
     */
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository, JwtTokenProvider jwtTokenProvider, UserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    public UserDtoOut createUser(UserDtoIn userDtoIn) {
        User user = userMapper.dtoInToEntity(userDtoIn, roleRepository);
        Role role = roleRepository.findById(userDtoIn.getRoleId()).orElseThrow(() -> new NotFoundException("Role not found"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role);
        User createdUser = userRepository.save(user);
        return userMapper.entityToDtoOut(createdUser);
    }

    public UserDtoOut getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(TextNotFound));
        return userMapper.entityToDtoOut(user);
    }

    public List<UserDtoOut> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::entityToDtoOut)
                .collect(java.util.stream.Collectors.toList());
    }

    public UserDtoOut updateUser(Long id, UserDtoIn userDtoIn) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException(TextNotFound);
        }
        User user = userMapper.dtoInToEntity(userDtoIn, roleRepository);
        user.setId(id);
        user.setDateCreated(userRepository.findById(id).get().getDateCreated());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User updatedUser = userRepository.save(user);
        return userMapper.entityToDtoOut(updatedUser);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException(TextNotFound);
        }
        userRepository.deleteById(id);
    }


}
