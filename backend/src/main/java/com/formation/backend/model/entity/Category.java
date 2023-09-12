package com.formation.backend.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "category")
@Getter
@Setter
@RestResource(exported = false)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique = true, name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "name")
    private Set<Product> products;
}
