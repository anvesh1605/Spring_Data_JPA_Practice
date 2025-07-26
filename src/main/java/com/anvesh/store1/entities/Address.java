package com.anvesh.store1.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
//@ToString(exclude = "user") // can mention here or also we can mention where user is defined
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "city",nullable = false)
    private String city;
    @Column(name="state",nullable = false)
    private String state;
    @Column(name="zip",nullable = false)
    private String zip;

    @ManyToOne
    @JoinColumn(name="user_id")
    @ToString.Exclude //other way
    private User user;



}
