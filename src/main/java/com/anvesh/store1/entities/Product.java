package com.anvesh.store1.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private Long name;

    @Column(name = "description",columnDefinition = "TEXT",nullable = false)
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "users")
    private Set<User>users = new HashSet<>();
}