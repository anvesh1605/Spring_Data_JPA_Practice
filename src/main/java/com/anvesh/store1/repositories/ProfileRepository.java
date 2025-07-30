package com.anvesh.store1.repositories;

import com.anvesh.store1.entities.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}