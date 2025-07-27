package com.anvesh.store1;

import com.anvesh.store1.entities.Address;
import com.anvesh.store1.entities.Tag;
import com.anvesh.store1.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Store1Application {

    public static void main(String[] args) {
//        SpringApplication.run(Store1Application.class, args);

//        var user = new User(1L,"Anvesh","email","password");
//        user.setId(1L);
//        user.setName("Anvesh");
//        user.setEmail("anvesh@gmail.com");
//        user.setPassword("password");

       var user = User.builder()
               .name("John")
               .password("password")
               .email("email")
               .build();
       var address = Address.builder()
               .street("street")
               .city("city")
               .state("state")
               .zip("zip")
               .build();
//       user.getAddresses().add(address);
//       address.setUser(user);
        user.addAddress(address);
        System.out.println(user);

        user.addTag("tag1");
        System.out.println(user);

    }

}
