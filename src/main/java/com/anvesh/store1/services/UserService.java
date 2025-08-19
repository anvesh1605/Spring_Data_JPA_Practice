package com.anvesh.store1.services;

import com.anvesh.store1.entities.Address;
import com.anvesh.store1.entities.Category;
import com.anvesh.store1.entities.Product;
import com.anvesh.store1.entities.User;
import com.anvesh.store1.repositories.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.catalina.Store;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ProfileRepository profileRepository;

    private final EntityManager entityManager;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
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

    @Transactional
    public void showRelatedEntities()
    {
//        var user= userRepository.findById(1L).orElseThrow();
        var profile= profileRepository.findById(2L).orElseThrow();
        // when this line starts transaction starts and when it ends transaction ends with these line
        System.out.println(profile.getBio());
        System.out.println(profile.getUser().getEmail());//this doesn't come in transaction
        //to tackle this issue, we keep this whole method as a transaction with the annotation
    }

    public void showAddresses()
    {
        var address = addressRepository.findById(1L).orElseThrow();
        System.out.println(address);
    }

    public void persistRelated()
    {
        var user = User.builder()
                .name("John Doe")
                .email("johnnn@gmial.com")
                .password("password")
                .build();
        var address = Address.builder()
                .street("street11")
                .city("city11")
                .state("state11")
                .zip("zip11")
                .build();
        user.addAddress(address);
        userRepository.save(user);
    }

    @Transactional
    public void deleteRelated()
    {
//        userRepository.deleteById(1L);
        var user = userRepository.findById(3L).orElseThrow();
        var address = user.getAddresses().get(0);
        user.removeAddress(address);
        userRepository.save(user);
    }

    @Transactional
    public void manageProducts()
    {
//        step 2 8.9
//        var category = categoryRepository.findById((byte) 1).orElseThrow();
//
//        var product = Product.builder()
//                .name(12L)
//                .description("dessc")
//                .price(BigDecimal.valueOf(15000.55))
//                .category(category)
//                .build();
//        productRepository.save(product);

        //step 3 8.9
//        var user = userRepository.findById(3L).orElseThrow();
//        var products = productRepository.findAll();
//        products.forEach(user::addFavouriteProduct);
//        userRepository.save(user);

        //step 4 8.9

        productRepository.deleteById(3L);
    }

    //for update methods wrap it in a transactional annotation
    @Transactional
    public void updateProductPrices()
    {
        productRepository.updatePriceByCategory(1,BigDecimal.valueOf(10));
    }

    public void fetchProducts()
    {
//        var products = productRepository.findByCategory(new Category((byte)1));// for getting all colums
        //will use dtos to retrieve particular column values

        var products = productRepository.findByCategory(new Category((byte)1));
        products.forEach(System.out::println);
    }
}
