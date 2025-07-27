package com.anvesh.store1.repositories;

import com.anvesh.store1.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
