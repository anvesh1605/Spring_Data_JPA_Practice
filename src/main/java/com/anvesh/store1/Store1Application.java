package com.anvesh.store1;

import com.anvesh.store1.entities.Address;
import com.anvesh.store1.entities.Profile;
import com.anvesh.store1.entities.Tag;
import com.anvesh.store1.entities.User;
import com.anvesh.store1.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Store1Application {

    public static void main(String[] args) {
        ApplicationContext context=SpringApplication.run(Store1Application.class, args);

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

       var profile = Profile.builder()
               .bio("bio")
                       .build();
//       user.getAddresses().add(address);
//       address.setUser(user);
        user.addAddress(address);
        System.out.println(user);

        user.addTag("tag1");
        System.out.println(user);

        user.addProfile(profile);
        System.out.println(user);


        //saving a model object in database
        var user1 = User.builder()
                .name("anvesh")
                .password("ppp1")
                .email("email@gmail.com")
                .build();
        var repository = context.getBean(UserRepository.class);
        repository.save(user1);
        user1.addProfile(profile);

//        var user_find =repository.findById(1L).orElseThrow(); // this is causing error
//        System.out.println(user11);// we will get an exception if we run like this,LazyInitializationException
//        System.out.println(user_find.getEmail());
    }

}
