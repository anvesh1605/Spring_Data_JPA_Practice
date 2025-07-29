package com.anvesh.store1.services;

import com.anvesh.store1.entities.User;
import com.anvesh.store1.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final EntityManager entityManager;
    // this "entityManager" is responsible for managing the entity persistence context

    @Transactional  // will make the boundary of transaction long
    public void showEntityStates()
    {
        var user = User.builder()
                .name("John Doe")
                .email("john@gmail.com")
                .password("password")
                .build();

        if(entityManager.contains(user)) {
            System.out.println("Persistent");
        }
        else
        {
            System.out.println("Transient/Detached");
        }

        userRepository.save(user);
        if(entityManager.contains(user)) {
            System.out.println("Persistent");
        }
        else
        {
            System.out.println("Transient/Detached");
        }
    }
}
