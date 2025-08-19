package com.anvesh.store1.repositories;

import com.anvesh.store1.dtos.UserSummary;
import com.anvesh.store1.entities.Profile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

//        @EntityGraph(attributePaths = "users")
//        List<Profile> findByLoyaltyPointsGreaterThan(int points);



}