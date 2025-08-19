package com.anvesh.store1.repositories;

import com.anvesh.store1.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

     //oneToMany and manyToMany always use Lazy loading
    //if we want to load some related entities we use fetch = fetchType.EAGER but all the realted entities
    //get loaded.to load only one particular realted enetity we use @EntityGraph on derived queries.

    @EntityGraph(attributePaths = {"tags","addresses"}) //only tags is loaded
    //we can also pass nested relations like if the address entity also has relation with country we can use
    //addresses.country
    Optional<User> findByName(String username);

}
