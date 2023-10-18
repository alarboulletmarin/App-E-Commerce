package com.formation.backend.model.entity;

import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
@Data
@RestResource(exported = false)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private Set<User> users;


}
