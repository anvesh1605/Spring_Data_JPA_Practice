package com.anvesh.store1.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "profiles")
public class Profile {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="bio",nullable = false)
    private String bio;
    @Column(name="phoneNumber",nullable = false)
    private String phoneNumber;
    @Column(name="dateOfBirth",nullable = false)
    private Date dateOfBirth;
    @Column(name="loyalty_Points",nullable = false)
    private int loyaltyPoints;
}
