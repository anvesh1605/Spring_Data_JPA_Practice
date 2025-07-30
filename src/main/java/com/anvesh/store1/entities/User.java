package com.anvesh.store1.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(nullable = false,name="name")
    private String name;
    @Column(nullable = false,name="email")
    private String email;
    @Column(nullable = false,name = "password")
    private String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.PERSIST)
    @Builder.Default
    @ToString.Exclude
    private List<Address> addresses = new ArrayList<>();//this kind of initializations are skipped
//    use the builder.default annotaion to tell take care of this kind of initializations when building an object
    public void addAddress(Address address) {
        addresses.add(address);
        address.setUser(this);
    }
    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setUser(null);
    }

    @ManyToMany
    @JoinTable(
            name = "user_tags",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @Builder.Default
    @ToString.Exclude
    private Set<Tag> tags = new HashSet<>();
    public void addTag(String tagName) {
        var tag1 = new Tag(tagName);
        tags.add(tag1);
        tag1.getUsers().add(this);
    }

    @OneToOne(mappedBy = "user")
    private Profile profile;

    public void addProfile(Profile profile) {
        this.profile = profile;
        profile.setUser(this);
    }

    @ManyToMany
    @JoinTable(
            name = "wishlist",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @ToString.Exclude
    private Set<Product> products = new HashSet<>();


}

